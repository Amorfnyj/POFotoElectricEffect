package controller;

import model.SynchronizedIntensity;
import model.SynchronizedVoltage;
import model.SynchronizedWaveLength;
import view.MainFrame;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Maksim Andrejewski on 18.06.2017.
 */
public class Controller {

    MainFrame view;
    private static SynchronizedVoltage voltage;
    private static SynchronizedWaveLength waveLength;
    private static SynchronizedIntensity intensity;

    private ElectronController electronController;
    private LightController lightController;


    public Controller(MainFrame view) {

        voltage = new SynchronizedVoltage();
        waveLength = new SynchronizedWaveLength();
        intensity = new SynchronizedIntensity();

        this.lightController = new LightController(waveLength, intensity, view.getSimulationPanel());
        this.lightController.start();

        this.electronController = new ElectronController(voltage, intensity, waveLength, view.getSimulationPanel());

        this.view = view;

        this.view.addStartButtonListener(new StartButtonListener(this.view, this.electronController));
        this.view.getConfigurationPanel().addVoltageSliderListener(new VoltageChangeListener(this.voltage, this.view));
        this.view.getConfigurationPanel().addWaveLengthSliderListener(new WaveLengthChangeListener(this.waveLength, this.view));
        this.view.getConfigurationPanel().addIntensitySliderListener(new intensityChangeListener(this.intensity, this.view));


        electronController.start();
    }

    class StartButtonListener implements ActionListener {

        private MainFrame view;
        private ElectronController electronController;

        StartButtonListener(MainFrame view, ElectronController electronController) {
            this.view = view;
            this.electronController = electronController;
        }
        public void actionPerformed(ActionEvent arg0) {
            try {
                if(electronController.isPause()) {
                    view.getConfigurationPanel().setStartButtonText("Pause");
                } else {
                    view.getConfigurationPanel().setStartButtonText("Start");
                }
                electronController.setPause(!electronController.isPause());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class WaveLengthChangeListener implements ChangeListener {

        private MainFrame view;
        private SynchronizedWaveLength waveLength;

        public WaveLengthChangeListener(SynchronizedWaveLength waveLength, MainFrame view) {
            this.view = view;
            this.waveLength = waveLength;
        }

        public void stateChanged(ChangeEvent arg0) {
            int waveLengthValue = view.getConfigurationPanel().getWaveLength();

            waveLength.setWaveLength(waveLengthValue);

            this.view.getConfigurationPanel().setWaveLengthValue(waveLengthValue);

        }
    }

    class intensityChangeListener implements ChangeListener {

        private MainFrame view;
        public SynchronizedIntensity intensity;

        intensityChangeListener(SynchronizedIntensity intensity, MainFrame view) {
            this.view = view;
            this.intensity = intensity;
        }

        public void stateChanged(ChangeEvent arg0) {
            int intensityValue = view.getConfigurationPanel().getIntensity();

            intensity.setIntensity(intensityValue);

            this.view.getConfigurationPanel().setIntensityValue(intensityValue);

        }
    }

    class VoltageChangeListener implements ChangeListener {

        private SynchronizedVoltage voltage;
        private MainFrame view;

        VoltageChangeListener(SynchronizedVoltage voltage, MainFrame view) {
            this.voltage = voltage;
            this.view = view;
        }

        public void stateChanged(ChangeEvent arg0) {
            double voltage = view.getConfigurationPanel().getVoltage() / 100;

            this.view.getConfigurationPanel().setVoltageValue(voltage);

            this.voltage.setVoltage(voltage);

        }
    }


}
