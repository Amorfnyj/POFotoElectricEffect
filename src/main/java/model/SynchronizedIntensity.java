package model;

/**
 * Created by Maksim Andrejewski on 19.06.2017.
 */
public class SynchronizedIntensity {
    private int intensity = 0;

    public synchronized int getIntensity() {
        return intensity;
    }

    public synchronized void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
