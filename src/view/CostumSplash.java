package view;

import javax.swing.*;
import java.awt.*;

public class CostumSplash extends JWindow {
    private Timer timer;
    private int busX = -100;
    private int cloudX1 = 0;
    private int cloudX2 = 400; 
    private int progress = 0;

    public CostumSplash() {
        setSize(800, 600);
        setLocationRelativeTo(null);

        timer = new Timer(50, e -> {
            busX += 10;
            if (busX > 800) {
                busX = -100;
            }

            cloudX1 -= 5;
            if (cloudX1 < -150) {
                cloudX1 = 800;
            }
            cloudX2 -= 5;
            if (cloudX2 < -150) {
                cloudX2 = 800;
            }

            // Update progress bar
            if (progress < 600) {
                progress += 14;
            }

            repaint();
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(135, 206, 235));
        g2.fillRect(0, 0, 800, 400);

        //clouds
        g2.setColor(Color.WHITE);
        g2.fillOval(cloudX1, 50, 100, 60);
        g2.fillOval(cloudX1 + 30, 70, 120, 50);
        g2.fillOval(cloudX2, 100, 100, 60);
        g2.fillOval(cloudX2 + 30, 120, 120, 50);

        //school
        g2.setColor(new Color(139, 69, 19));
        g2.fillRect(300, 250, 200, 150);
        g2.setColor(Color.GRAY);
        g2.fillRect(380, 340, 40, 60);
        g2.setColor(Color.BLACK);
        g2.fillRect(398, 340, 4, 60);
        g2.setColor(new Color(210, 180, 140));
        g2.fillPolygon(new int[] { 250, 550, 450, 350 }, new int[] { 250, 250, 200, 200 }, 4);
        g2.setColor(Color.WHITE);
        g2.fillRect(350, 280, 30, 30);
        g2.setColor(Color.BLACK);
        g2.fillRect(350, 294, 30, 2);
        g2.fillRect(364, 280, 2, 30);
        g2.setColor(Color.WHITE);
        g2.fillRect(420, 280, 30, 30);
        g2.setColor(Color.BLACK);
        g2.fillRect(420, 294, 30, 2);
        g2.fillRect(434, 280, 2, 30);

        //the Moroccan flag
        g2.setColor(new Color(193, 39, 45));
        g2.fillRect(350, 150, 100, 60);
        g2.setColor(Color.GREEN);
        int[] xPoints = { 400, 404, 415, 406, 409, 400, 391, 394, 385, 396 };
        int[] yPoints = { 165, 174, 174, 181, 192, 184, 192, 181, 174, 174 };
        g2.setColor(Color.GREEN);
        g2.fillPolygon(xPoints, yPoints, xPoints.length);
        g2.setColor(Color.GREEN);
        g2.drawPolygon(xPoints, yPoints, xPoints.length);

        // the road
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 400, 800, 100);
        g2.setColor(Color.WHITE);
        for (int i = 0; i < 800; i += 80) {
            g2.fillRect(i, 445, 40, 10);
        }

        //school bus
        g2.setColor(Color.YELLOW);
        g2.fillRect(busX, 450, 100, 30);
        g2.fillRect(busX, 430, 90, 20);
        g2.setColor(Color.BLACK);
        g2.fillRect(busX + 15, 440, 20, 20);
        g2.fillRect(busX + 55,440, 20, 20);
        g2.fillOval(busX + 70, 470, 20, 20);
        g2.fillOval(busX + 10, 470, 20, 20);

        // the progress bar
        g2.setColor(Color.BLUE);
        g2.fillRect(100, 520, progress, 30);
        g2.setColor(Color.BLACK);
        g2.drawRect(100, 520, 600, 30);
    }

}
