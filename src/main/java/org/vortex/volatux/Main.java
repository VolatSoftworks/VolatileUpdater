package org.vortex.volatux;

import org.vortex.volatux.elements.LauncherFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LauncherFrame().setVisible(true);
        });
    }
}