package view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

import static common.Constants.*;

/**
 * Created by Maksim Andrejewski on 18.06.2017.
 */
public class ConfigurationPanel extends JPanel {

    private JButton startButton;

    private JSlider waveLengthSlider;
    private JSlider intensitySlider;
    private JSlider voltageSlider;

    private JLabel waveLengthLabel;
    private JLabel voltageLabel;
    private JLabel intensityLabel;

    private JTextField voltageTextField;
    private JTextField waveLengthTextField;
    private JTextField intensityTextField;

    public ConfigurationPanel() {

        initConfiguration();

        initStartButton();

        initSliders();

        initLabels();

        initTextFields();

    }

    private void initStartButton() {
        startButton = new JButton("Pause");
        startButton.setBounds(10, FRAME_HEIGHT - 90, 165, 50);

        this.add(startButton);
    }

    private void initTextFields() {
        waveLengthTextField = new JTextField(String.valueOf(MIN_WAVE_LENGTH));
        waveLengthTextField.setEditable(false);
        waveLengthTextField.setBounds(10, 60, 40, 20);
        this.add(waveLengthTextField);

        voltageTextField = new JTextField("0");
        voltageTextField.setEditable(false);
        voltageTextField.setBounds(70, 60, 40, 20);
        this.add(voltageTextField);

        intensityTextField = new JTextField("0%");
        intensityTextField.setEditable(false);
        intensityTextField.setBounds(135, 60, 40, 20);
        this.add(intensityTextField);
    }

    private void initLabels() {
        waveLengthLabel = new JLabel("Wave Length");
        waveLengthLabel.setBounds(0, 35, 100, 20);
        waveLengthLabel.setFont(new Font("Wave Length", Font.PLAIN, 11));
        this.add(waveLengthLabel);

        voltageLabel = new JLabel("Voltage");
        voltageLabel.setBounds(70, 35, 100, 20);
        voltageLabel.setFont(new Font("Wave Length", Font.PLAIN, 11));
        this.add(voltageLabel);

        intensityLabel = new JLabel("Intensity");
        intensityLabel.setBounds(135, 35, 100, 20);
        intensityLabel.setFont(new Font("Wave Length", Font.PLAIN, 11));
        this.add(intensityLabel);
    }

    private void initConfiguration() {
        this.setLayout(null);
    }

    private void initSliders() {
        waveLengthSlider = new JSlider(JSlider.VERTICAL, MIN_WAVE_LENGTH, MAX_WAVE_LENGTH, MIN_WAVE_LENGTH);
        waveLengthSlider.setBounds(20, 80, 20, 350);
        this.add(waveLengthSlider);

        voltageSlider = new JSlider(JSlider.VERTICAL, MIN_VOLTAGE, MAX_VOLTAGE, 0);
        voltageSlider.setBounds(80, 80, 20, 350);
        this.add(voltageSlider);

        intensitySlider = new JSlider(JSlider.VERTICAL, MIN_INTENSITY, MAX_INTENSITY, 0);
        intensitySlider.setBounds(145, 80, 20, 350);
        this.add(intensitySlider);
    }

    public void addStartButtonListener(ActionListener startButtonListener) {
        startButton.addActionListener(startButtonListener);
    }

    public void addWaveLengthSliderListener(ChangeListener waveLengthSliderListener) {
        waveLengthSlider.addChangeListener(waveLengthSliderListener);
    }

    public void addVoltageSliderListener(ChangeListener voltageSliderListener) {
        voltageSlider.addChangeListener(voltageSliderListener);
    }

    public void addIntensitySliderListener(ChangeListener intensitySliderListener) {
        intensitySlider.addChangeListener(intensitySliderListener);
    }

    public int getWaveLength() {
        return waveLengthSlider.getValue();
    }

    public double getVoltage() {
        return (double) voltageSlider.getValue();
    }

    public int getIntensity() {
        return intensitySlider.getValue();
    }


    public void setVoltageValue(double voltageValue) {
        voltageTextField.setText(String.valueOf(voltageValue));
    }

    public void setWaveLengthValue(int waveLengthValue) {
        waveLengthTextField.setText(String.valueOf(waveLengthValue));
    }

    public void setIntensityValue(int intensityValue) {
        intensityTextField.setText(String.valueOf(intensityValue) + "%");
    }

    public void setStartButtonText(String text) {
        startButton.setText(text);
    }
}
