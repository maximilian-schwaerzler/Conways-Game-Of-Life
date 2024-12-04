package at.co.schwaerzler.maximilian;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements MouseListener, KeyListener {
    public static final int DEFAULT_GAME_WIDTH = 50;
    public static final int DEFAULT_GAME_HEIGHT = 50;

    private GameOfLife game;
    private GamePanel gp;

    public GameFrame() throws HeadlessException {
        super();

        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException("In headless environment. This is a game, what were you thinking?");
        }


        // setSize(500, 500);
        gp = new GamePanel(500, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conway's Game of Life");
        addMouseListener(this);
        addKeyListener(this);
        setResizable(false);

        // setMinimumSize(new Dimension(200, 200));

        getContentPane().add(gp);
        pack();
        game = new GameOfLife();
    }

    public Cell getCellFromPoint(Point p) {
        int cellXPos = (getWidth() / DEFAULT_GAME_WIDTH) * p.x;
        int cellYPos = getHeight() / DEFAULT_GAME_HEIGHT * p.y;
        return new Cell(cellXPos, cellYPos);
    }

    // Update the window with the game info
    public void updateWindow() {
        for (Cell cell : game.getAliveCells()) {
            // TODO
        }
    }

    /*
    MOUSE LISTENERS
     */

    @Override
    public void mouseClicked(@NotNull MouseEvent e) {
        Cell clickedCell = getCellFromPoint(e.getPoint());
        game.toggleCell(clickedCell);
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

    /*
    KEY LISTENERS
     */

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
