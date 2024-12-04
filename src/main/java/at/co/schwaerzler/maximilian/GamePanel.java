package at.co.schwaerzler.maximilian;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private int gameWidth;
    private int gameHeight;

    public GamePanel(int gameW, int gameH) {
        this.setBackground(Color.BLACK);
        gameWidth = gameW;
        gameHeight = gameH;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gameWidth, gameHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 100, 100);
    }
}
