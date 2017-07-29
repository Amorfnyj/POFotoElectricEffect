package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static common.Constants.FRAME_HEIGHT;
import static common.Constants.FRAME_WIDTH;

/**
 * Created by Maksim Andrejewski on 18.06.2017.
 */
public class MainFrame extends JFrame {

    JSplitPane splitPane;
    SimulationPanel simulationPanel;
    ConfigurationPanel configurationPanel;

    public MainFrame() {

        initFrameConfig();

        initComponentsConfig();


    }

    private void initComponentsConfig() {

        simulationPanel = new SimulationPanel();
        configurationPanel = new ConfigurationPanel();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(simulationPanel);
        splitPane.setRightComponent(configurationPanel);

        splitPane.setEnabled(false);


        this.add(splitPane);
    }

    private void initFrameConfig() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setTitle("Photoelectric Effect");
        this.setResizable(false);
        this.setLayout(new BorderLayout());
    }

    public void addStartButtonListener(ActionListener startButtonListener) {

        configurationPanel.addStartButtonListener(startButtonListener);

    }

    public SimulationPanel getSimulationPanel() {
        return simulationPanel;
    }

    public ConfigurationPanel getConfigurationPanel() {
        return configurationPanel;
    }
}
