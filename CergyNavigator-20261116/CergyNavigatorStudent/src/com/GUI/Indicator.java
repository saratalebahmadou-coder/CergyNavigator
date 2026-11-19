package com.GUI;

import javax.swing.*;
import java.awt.*;

public class Indicator extends JComponent {
    private boolean value = false;

    public void setValue(boolean value) {
        this.value = value;
        this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(16, 16); // par exemple
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(value?Color.GREEN:Color.RED);
        g.fillOval(0,0,16,16);
    }

}
