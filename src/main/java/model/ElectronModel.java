package model;

import java.util.Random;

import static common.Constants.CANVAS_HEIGHT;

/**
 * Created by Maksim Andrejewski on 18.06.2017.
 */
public class ElectronModel {

    private static Random randomNumberGenerator = new Random();

    private int x;
    private int y;

    private int electronStartWaveLength;

    private int timer;

    public ElectronModel(int electronStartWaveLength) {
        this.x = 127;
        this.y = randomNumberGenerator.nextInt(CANVAS_HEIGHT - 10) + 80;
        this.timer = 0;
        this.electronStartWaveLength = electronStartWaveLength;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTimer() {
        return timer;
    }

    public void incTimer() {
        timer++;
    }

    public int getElectronStartWaveLength() {
        return electronStartWaveLength;
    }

    public void setElectronStartWaveLength(int electronStartWaveLength) {
        this.electronStartWaveLength = electronStartWaveLength;
    }
}
