package com.ronreynolds.graphics;

import lombok.extern.slf4j.Slf4j;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

@Slf4j
public class Main {
    private static final Runnable createMainFrame = () -> {
        log.info("creating main application frame");
        // top-level application frame
        var frame = new JFrame("Graphics Example");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // centered on screen

        // Additional graphics setup can be done here
        frame.add(new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Custom painting code goes here
                updateGraphics(g);
            }
        });
        frame.setVisible(true);
        log.info("main application frame visible");
    };

    /** this creates a static scene with some basic shapes and text */
    private static void updateGraphics(Graphics g) {
        log.info("updating graphics");
        // Cast Graphics object to Graphics2D for enhanced 2D features
        Graphics2D g2d = (Graphics2D) g;

        // Set rendering hints for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw a red rectangle
        g2d.setColor(Color.RED);
        g2d.fillRect(50, 50, 100, 75); // x, y, width, height

        // Draw a blue circle
        g2d.setColor(Color.BLUE);
        g2d.fill(new Ellipse2D.Double(200, 50, 80, 80)); // x, y, width, height

        // Draw a green line
        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(3)); // Set line thickness
        g2d.drawLine(50, 200, 250, 200); // x1, y1, x2, y2

        // Draw some text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Serif", Font.BOLD, 20));
        g2d.drawString("Hello Java 2D!", 70, 250); // text, x, y
        log.info("graphics updated");
    }

    public static void main(String[] args) {
        // enqueue the GUI creation on the NON-DAEMON Event Dispatch Thread (which is why the JVM doesn't exit after main())
        SwingUtilities.invokeLater(createMainFrame);
        log.info("main method completed");
    }
}