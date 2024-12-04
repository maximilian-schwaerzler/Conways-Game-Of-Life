package at.co.schwaerzler.maximilian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener, KeyListener {
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

    public Cell getCellFromPoint(Point p) {
        int cellXPos = (int) (((double) p.x / (double) getWidth()) * gameWidth);
        int cellYPos = (int) (((double) p.y / (double) getHeight()) * gameHeight);
        return new Cell(cellXPos, cellYPos);
    }

    public void updateWindow() {
        paintComponent(getGraphics());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gol.evolve();
            updateWindow();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Cell clickedCell = getCellFromPoint(e.getPoint());
        gol.toggleCell(clickedCell);
        updateWindow();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
