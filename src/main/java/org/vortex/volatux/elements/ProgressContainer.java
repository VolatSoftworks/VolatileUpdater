package org.vortex.volatux.elements;

import javax.swing.*;
import java.awt.*;

public class ProgressContainer extends JPanel {
    public ProgressContainer(JProgressBar progressBar) {
        setLayout(new BorderLayout());
        JLabel status = new JLabel("Checking JavaFX...");
        status.setFont(Font.getFont(Font.MONOSPACED));
        status.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        status.setForeground(Color.white);
        setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 20));
        setOpaque(false);
        add(status, BorderLayout.NORTH);
        add(progressBar, BorderLayout.CENTER);
    }
}
