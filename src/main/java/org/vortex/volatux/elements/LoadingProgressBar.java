package org.vortex.volatux.elements;

import javax.swing.*;
import java.awt.*;

public class LoadingProgressBar extends JProgressBar {

    public LoadingProgressBar() {
        super(0, 100);
        setValue(0);
        setOpaque(false);
        setBorder(null);
        setPreferredSize(new Dimension(1, 3));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g2.setColor(new Color(0x252525));
        g2.fillRect(0, 0, w, h);

        int fill = (int) (w * getPercentComplete());
        if (fill > 0) {
            g2.setColor(new Color(0x0096FF));
            g2.fillRect(0, 0, fill, h);
        }

        g2.dispose();
    }
}
