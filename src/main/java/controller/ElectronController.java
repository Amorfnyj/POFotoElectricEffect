package controller;

import model.ElectronModel;
import model.SynchronizedIntensity;
import model.SynchronizedVoltage;
import model.SynchronizedWaveLength;
import view.SimulationPanel;


import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;

import static common.Constants.*;
import static common.Constants.ELECTRON_VOLT_CONVERSION;


/**
 * Created by Maksim Andrejewski on 22.06.2017.
 */
public class ElectronController extends Thread {

    private SynchronizedVoltage voltage;
    private SynchronizedWaveLength waveLength;
    private SynchronizedIntensity intensity;

    private ArrayList<ElectronModel> electronsList;

    private Random randomNumberGenerator = new Random();
    private int timeInterval = 4;

    private SimulationPanel canvas;

    private boolean pause;


    ElectronController(SynchronizedVoltage voltage, SynchronizedIntensity intensity, SynchronizedWaveLength waveLength, SimulationPanel canvas) {
        this.voltage = voltage;
        this.waveLength = waveLength;
        this.intensity = intensity;
        this.canvas = canvas;
        this.pause = false;
        electronsList = new ArrayList<ElectronModel>();

        canvas.setElectrons(electronsList);

    }

    public void run() {
        try {

            int numberOfNewElectrons = 0;
            int timer = timeInterval;
            int electronStartWaveLength = 0;
            while (true) {
                if (!isPause()) {

                    canvas.setPolarity(this.getPolarity());

                    if (timer == 0) {
                        timer = timeInterval;
                        electronStartWaveLength = waveLength.getWaveLength();
                        numberOfNewElectrons = checkIntensity();

                        emitNewElectrons(numberOfNewElectrons, electronStartWaveLength);
                    }

                    calculateAndPrintElectronPosition();

                    timer--;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Info: electron interrupted");
        } catch (ConcurrentModificationException e) {
            System.out.println("Error: synchronization error");
        }
    }

    private polarity getPolarity() {
        if (voltage.getVoltage() == 0) {
            return polarity.ZERO;
        } else if (voltage.getVoltage() < 0) {
            return polarity.MINUS;
        } else if (voltage.getVoltage() > 0) {
            return polarity.PLUS;
        }
        return polarity.ZERO;
    }

    private void emitNewElectrons(int numberOfNewElectrons, int electronStartWaveLength) {
        if (electronStartWaveLength > 580) {
            return;
        }

        for (int i = 0; i < numberOfNewElectrons; i++) {
            emitElectron(electronStartWaveLength);
        }
    }

    private void calculateAndPrintElectronPosition() throws InterruptedException, ConcurrentModificationException {
        int positionX;

        for (Iterator<ElectronModel> iterator = electronsList.iterator(); iterator.hasNext(); ) {
            ElectronModel electron = iterator.next();
            electron.incTimer();
            positionX = moveWithAcceleration(electron.getTimer() * TIME, electron.getElectronStartWaveLength()) + 125;
            if (positionX > (CANVAS_WIDTH + 123) || positionX < 123) {
                iterator.remove();
            }
            electron.setX(positionX);
        }
        canvas.repaint();

        sleep(TIME);
    }

    private int checkIntensity() {
        int intensityValue = intensity.getIntensity();
        int numberOfElectron = 0;
        if (intensityValue == 0) {
            return numberOfElectron;
        } else if (intensityValue >= 1 && intensityValue <= 10) {
            numberOfElectron = randomNumberGenerator.nextInt(3) + 1;
            timeInterval = 10;
        } else if (intensityValue > 10 && intensityValue <= 30) {
            numberOfElectron = randomNumberGenerator.nextInt(5) + 1;
            timeInterval = 7;
        } else if (intensityValue > 30 && intensityValue <= 60) {
            numberOfElectron = randomNumberGenerator.nextInt(7) + 1;
            timeInterval = 5;
        } else if (intensityValue > 60 && intensityValue <= 90) {
            numberOfElectron = randomNumberGenerator.nextInt(9) + 1;
            timeInterval = 3;
        } else if (intensityValue > 90) {
            numberOfElectron = randomNumberGenerator.nextInt(10) + 1;
            timeInterval = 3;
        }

        return numberOfElectron;
    }

    private int moveWithAcceleration(int t, int electronStartWaveLength) {
        return (int) (((getKmax(electronStartWaveLength)) * t * Math.pow(10, -2)) + voltage.getVoltage() * t * t * Math.pow(10, -5) / 2);
    }


    public float getKmax(int electronStartWaveLength) {
        float waveLengthValue = (float) (electronStartWaveLength * Math.pow(10, -10));
        double A = 2.14;
        float kmax = (float) (((PLANCK_CONSTANT * SPEED_OF_LIGHT) / (waveLengthValue * ELECTRON_VOLT_CONVERSION)) - A) / 4;
        return kmax;
    }

    public void emitElectron(int electronStartWaveLength) {
        ElectronModel electron = new ElectronModel(electronStartWaveLength);
        electronsList.add(electron);
        canvas.addElectron(electron);
    }

    public synchronized boolean isPause() {
        return pause;
    }

    public synchronized void setPause(boolean pause) throws InterruptedException {
        this.pause = pause;
    }
}
