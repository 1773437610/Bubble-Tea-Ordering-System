package ui;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
    public AppPanel() {
        setSize(new Dimension(1000, 1000));
        setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.drawRect(JPanel.WIDTH, JPanel.HEIGHT, JPanel.WIDTH/4, JPanel.HEIGHT/4);
        drawBackground(g);

    }

    private void drawBackground(Graphics g) {
    }


}
