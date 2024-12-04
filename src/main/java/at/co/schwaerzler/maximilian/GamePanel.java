package at.co.schwaerzler.maximilian;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final int windowWidth;
    private final int windowHeight;

    private final int gameWidth;
    private final int gameHeight;

    private final int cellW;
    private final int cellH;

    public GameOfLife gol;

    public GamePanel(int windowW, int windowH, int gameW, int gameH) {
        this.setBackground(Color.BLACK);
        windowWidth = windowW;
        windowHeight = windowH;
        gameWidth = gameW;
        gameHeight = gameH;
        cellW = windowW / gameWidth;
        cellH = windowH / gameHeight;
        gol = new GameOfLife();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(windowWidth, windowHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (Cell cell : gol.getAliveCells()) {
            g.fillRect(cell.x * cellW, cell.y * cellH, cellW, cellH);
        }
    }
}
