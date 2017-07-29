package common;

/**
 * Created by Maksim Andrejewski on 19.06.2017.
 */
public class Constants {

    public static int FRAME_WIDTH = 800;
    public static int FRAME_HEIGHT = 600;
    public static int SIMULATION_PANEL_WIDTH = 600;
    public static int SIMULATION_PANEL_HEIGHT = 600;
    public static int CANVAS_WIDTH = 337;
    public static int CANVAS_HEIGHT = 170;
    public static int SPEED_OF_LIGHT = (int) (3 * Math.pow(10, 8));
    public static float PLANCK_CONSTANT = (float) (6.62 * Math.pow(10, -34));
    public static float MASS_OF_ELECTRON = (float) (9.10938356 * Math.pow(10, -31));
    public static int MIN_WAVE_LENGTH = 350;
    public static int MAX_WAVE_LENGTH = 760;

    public static int MIN_VOLTAGE = -200;
    public static int MAX_VOLTAGE = 200;
    public static int MIN_INTENSITY = 0;
    public static int MAX_INTENSITY = 100;
    public static float ELECTRON_VOLT_CONVERSION = (float) (1.6 * Math.pow(10, -19));
    public static int TIME = 30;

    public enum polarity {ZERO, PLUS, MINUS};

}
