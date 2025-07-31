package org.vortex.volatux;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setUndecorated(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel(new BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                }
            };
            mainPanel.setBackground(new Color(0x141414));
            mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            ImageIcon scaledIcon = new ImageIcon(new ImageIcon(Objects.requireNonNull(Main.class.getResource("/VOLATILE_NOBG.png")))
                    .getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(scaledIcon, SwingConstants.CENTER);


            JProgressBar progressBar = getJProgressBar();

            Point[] dragPoint = new Point[1];
            mainPanel.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    dragPoint[0] = e.getPoint();
                }
            });
            mainPanel.addMouseMotionListener(new MouseAdapter() {
                public void mouseDragged(MouseEvent e) {
                    frame.setLocation(e.getXOnScreen() - dragPoint[0].x, e.getYOnScreen() - dragPoint[0].y);
                }
            });

            frame.add(mainPanel);
            mainPanel.add(imageLabel, BorderLayout.CENTER);

            // Add progress bar with padding
            JPanel progressContainer = new JPanel(new BorderLayout());
            progressContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 20)); // Horizontal margins
            progressContainer.setOpaque(false);
            progressContainer.add(progressBar, BorderLayout.CENTER);
            mainPanel.add(progressContainer, BorderLayout.SOUTH);

            frame.setSize(320, 480);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JProgressBar getJProgressBar() {
        JProgressBar progressBar = new JProgressBar() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw track (darker background)
                g2d.setColor(new Color(0x252525));
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Draw progress (blue fill)
                int width = (int) (getWidth() * (getValue() / 100.0));
                if (width > 0) {
                    g2d.setColor(new Color(0x0096FF));
                    g2d.fillRect(0, 0, width, getHeight());
                }
                g2d.dispose();
            }
        };
        progressBar.setValue(45);
        progressBar.setPreferredSize(new Dimension(0, 3));
        progressBar.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Margins
        progressBar.setOpaque(false);
        return progressBar;
    }
}