package org.vortex.volatux.elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class LauncherFrame extends JFrame {

    private Point dragPoint;
    private final Color backgroundColor = new Color(0x141414);

    public LauncherFrame() {
        setUndecorated(true); // Remove X
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Make sure to terminate process on exit.
        JPanel mainPanel = getMainPanel(); // Define panel to organize elements

        // Get image from resources
        ImageIcon icon = new ImageIcon(
                Objects.requireNonNull(
                        getClass().getResource("/VOLATILE_NOBG.png")
                )
        );

        // Scale image
        Image scaled = icon.getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH);

        // Create JLabel element
        JLabel imageLabel = new JLabel(new ImageIcon(scaled), SwingConstants.CENTER);

        // Progress bar and its margin
        LoadingProgressBar progressBar = new LoadingProgressBar();
        JPanel progressContainer = new ProgressContainer(progressBar);

        // Put things together

        mainPanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(progressContainer, BorderLayout.SOUTH);
        add(mainPanel);

        setSize(320, 480);
        setLocationRelativeTo(null);
    }

    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Mouse drag logic
        MouseAdapter dragAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragPoint = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(
                        e.getXOnScreen() - dragPoint.x,
                        e.getYOnScreen() - dragPoint.y
                );
            }
        };
        mainPanel.addMouseListener(dragAdapter);
        mainPanel.addMouseMotionListener(dragAdapter);
        return mainPanel;
    }
}