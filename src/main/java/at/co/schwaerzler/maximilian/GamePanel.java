package at.co.schwaerzler.maximilian;

import org.jetbrains.annotations.NotNull;

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

    private boolean isPaused = true;
    private final Timer gameTickTimer;

    public GamePanel(int windowW, int windowH, int gameW, int gameH) {
        setBackground(Color.BLACK);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        windowWidth = windowW;
        windowHeight = windowH;
        gameWidth = gameW;
        gameHeight = gameH;
        cellW = windowW / gameWidth;
        cellH = windowH / gameHeight;
        gol = new GameOfLife();

        gameTickTimer = new Timer(50, _ -> gameTick());
        gameTickTimer.setInitialDelay(0);
        setPaused(isPaused);
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

    public Cell getCellFromPoint(@NotNull Point p) {
        int cellXPos = (int) (((double) p.x / (double) getWidth()) * (double) gameWidth);
        int cellYPos = (int) (((double) p.y / (double) getHeight()) * gameHeight);
        return new Cell(cellXPos, cellYPos);
    }

    private void gameTick() {
        gol.evolve();
        updateWindow();
    }

    public void updateWindow() {
        repaint();
    }

    private void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            gameTickTimer.stop();
        } else {
            gameTickTimer.restart();
        }
    }

    private void setPaused(boolean value) {
        isPaused = value;
        if (isPaused) {
            gameTickTimer.stop();
        } else {
            gameTickTimer.restart();
        }
    }

    @Override
    public void mouseClicked(@NotNull MouseEvent e) {
        Cell clickedCell = getCellFromPoint(e.getPoint());
        gol.toggleCell(clickedCell);
        updateWindow();
    }

    @Override
    public void keyPressed(@NotNull KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
            gameTick();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            togglePause();
        } else if (keyCode == KeyEvent.VK_R) {
            gol.resetGame();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
