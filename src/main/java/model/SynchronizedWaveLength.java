package model;

/**
 * Created by Maksim Andrejewski on 19.06.2017.
 */
public class SynchronizedWaveLength {
    private int waveLength = 350;

    public synchronized int getWaveLength() {
        return waveLength;
    }

    public synchronized void setWaveLength(int waveLength) {
        this.waveLength = waveLength;
    }
}
