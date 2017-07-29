package model;

/**
 * Created by Maksim Andrejewski on 19.06.2017.
 */
public class SynchronizedVoltage {
    private double voltage = 0;

    public synchronized void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public synchronized double getVoltage() {
        return voltage;
    }
}
