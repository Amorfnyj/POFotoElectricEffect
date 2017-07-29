package controller;

import model.SynchronizedIntensity;
import model.SynchronizedWaveLength;
import view.SimulationPanel;

/**
 * Created by Maksim Andrejewski on 25.06.2017.
 */
public class LightController extends Thread {

    static private double Gamma = 0.80;
    static private double IntensityMax = 255;
    private SynchronizedWaveLength waveLength;
    private SynchronizedIntensity intensity;
    private SimulationPanel canvas;

    public LightController(SynchronizedWaveLength waveLength, SynchronizedIntensity intensity, SimulationPanel canvas) {
        this.waveLength = waveLength;
        this.intensity = intensity;
        this.canvas = canvas;
    }

    public static int[] waveLengthToRGB(double Wavelength) {
        double factor;
        double Red, Green, Blue;

        if ((Wavelength >= 380) && (Wavelength < 440)) {
            Red = -(Wavelength - 440) / (440 - 380);
            Green = 0.0;
            Blue = 1.0;
        } else if ((Wavelength >= 440) && (Wavelength < 490)) {
            Red = 0.0;
            Green = (Wavelength - 440) / (490 - 440);
            Blue = 1.0;
        } else if ((Wavelength >= 490) && (Wavelength < 510)) {
            Red = 0.0;
            Green = 1.0;
            Blue = -(Wavelength - 510) / (510 - 490);
        } else if ((Wavelength >= 510) && (Wavelength < 580)) {
            Red = (Wavelength - 510) / (580 - 510);
            Green = 1.0;
            Blue = 0.0;
        } else if ((Wavelength >= 580) && (Wavelength < 645)) {
            Red = 1.0;
            Green = -(Wavelength - 645) / (645 - 580);
            Blue = 0.0;
        } else if ((Wavelength >= 645) && (Wavelength < 781)) {
            Red = 1.0;
            Green = 0.0;
            Blue = 0.0;
        } else {
            Red = 0.0;
            Green = 0.0;
            Blue = 0.0;
        }
        ;

        // Let the intensity fall off near the vision limits

        if ((Wavelength >= 380) && (Wavelength < 420)) {
            factor = 0.3 + 0.7 * (Wavelength - 380) / (420 - 380);
        } else if ((Wavelength >= 420) && (Wavelength < 701)) {
            factor = 1.0;
        } else if ((Wavelength >= 701) && (Wavelength < 781)) {
            factor = 0.3 + 0.7 * (780 - Wavelength) / (780 - 700);
        } else {
            factor = 0.0;
        }
        ;


        int[] rgb = new int[3];

        // Don't want 0^x = 1 for x <> 0
        rgb[0] = Red == 0.0 ? 0 : (int) Math.round(IntensityMax * Math.pow(Red * factor, Gamma));
        rgb[1] = Green == 0.0 ? 0 : (int) Math.round(IntensityMax * Math.pow(Green * factor, Gamma));
        rgb[2] = Blue == 0.0 ? 0 : (int) Math.round(IntensityMax * Math.pow(Blue * factor, Gamma));

        return rgb;
    }

    public void run() {
        try {
            double waveLengthValue;
            int[] rgb;
            float intensityValue;
            while (true) {
                waveLengthValue = new Double(waveLength.getWaveLength());

                rgb = waveLengthToRGB(waveLengthValue);

                intensityValue = (float) (intensity.getIntensity()) / 100;

                this.canvas.setIntensity(intensityValue);
                this.canvas.setRgb(rgb);


            }
        } catch (Exception e) {
            System.out.println("Info: light interrupted");
        }
    }
}
