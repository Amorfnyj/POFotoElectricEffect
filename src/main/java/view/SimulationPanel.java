package view;

import common.Constants;
import model.ElectronModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static common.Constants.SIMULATION_PANEL_HEIGHT;
import static common.Constants.SIMULATION_PANEL_WIDTH;

/**
 * Created by Maksim Andrejewski on 18.06.2017.
 */
public class SimulationPanel extends JPanel {

    private static List<ElectronModel> electrons;

    private BufferedImage backgroundImage = null;

    private int[] rgb;
    private float intensity;

    private Constants.polarity polarity;

    public SimulationPanel() {

        initPanelConfig();

    }

    private void initPanelConfig() {
        setBackgroundImage();
        this.setLayout(null);
        this.setBackground(Color.CYAN);
        this.setMinimumSize(new Dimension(SIMULATION_PANEL_WIDTH, SIMULATION_PANEL_HEIGHT));
    }

    private void setBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/SimulationBackgroundImage.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        this.requestFocusInWindow();

        printPolarity(g);

        paintLight(g);

        paintElectrons(g);

    }

    private void printPolarity(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 1));

        if (polarity == Constants.polarity.ZERO) {
            g2.setPaint(Color.BLACK);
            g2.fillRect(120, 80, 10, 170);
            g2.fillRect(261, 446, 2, 90);

            g2.setPaint(Color.BLACK);
            g2.fillRect(465, 80, 10, 170);
            g2.fillRect(330, 446, 2, 90);
        } else if (polarity == Constants.polarity.PLUS) {
            g2.setPaint(Color.BLUE);
            g2.fillRect(120, 80, 10, 170);
            g2.fillRect(261, 446, 2, 90);

            g2.setPaint(Color.RED);
            g2.fillRect(465, 80, 10, 170);
            g2.fillRect(330, 446, 2, 90);
        } else if (polarity == Constants.polarity.MINUS) {
            g2.setPaint(Color.RED);
            g2.fillRect(120, 80, 10, 170);
            g2.fillRect(261, 446, 2, 90);

            g2.setPaint(Color.BLUE);
            g2.fillRect(465, 80, 10, 170);
            g2.fillRect(330, 446, 2, 90);
        }

    }

    private void paintElectrons(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 0.6f));

        try {
            if (electrons != null) {
                for (ElectronModel electron : electrons) {
                    Ellipse2D.Float electronView = new Ellipse2D.Float(electron.getX(), electron.getY(), 8, 8);
                    g2.setPaint(new Color(0, 128, 128)); // a dull blue-green
                    g2.fill(electronView);
                    g2.draw(electronView);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Error: synchronization error");
        }
    }

    private void paintLight(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int xpoints[] = {213, 382, 128, 128};
        int ypoints[] = {0, 0, 250, 85};
        int npoints = 4;

        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, intensity));
        g2d.setColor(new Color(rgb[0], rgb[1], rgb[2]));

        g2d.fillPolygon(xpoints, ypoints, npoints);

    }

    public void addElectron(ElectronModel electron) {
        electrons.add(electron);
    }

    public void setElectrons(List<ElectronModel> electrons) {
        this.electrons = electrons;
    }

    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public void setPolarity(Constants.polarity polarity) {
        this.polarity = polarity;
    }
}
