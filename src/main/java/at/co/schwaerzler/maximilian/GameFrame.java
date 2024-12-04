package at.co.schwaerzler.maximilian;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements MouseListener, KeyListener {
    public static final int DEFAULT_GAME_WIDTH = 10;
    public static final int DEFAULT_GAME_HEIGHT = 10;

//    private GameOfLife game;
    private GamePanel gp;

    public GameFrame() throws HeadlessException {
        super();

        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException("In headless environment. This is a game, what were you thinking?");
        }


        // setSize(500, 500);
        gp = new GamePanel(500, 500, DEFAULT_GAME_WIDTH, DEFAULT_GAME_HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conway's Game of Life");
        addMouseListener(this);
        addKeyListener(this);
        setResizable(false);

        // setMinimumSize(new Dimension(200, 200));

        getContentPane().add(gp);
        pack();
//        game = new GameOfLife();
    }

    public Cell getCellFromPoint(Point p) {
        int cellXPos = (int) (((double)p.x / (double)gp.getWidth()) * DEFAULT_GAME_WIDTH);
        int cellYPos = (int) (((double)p.y / (double)gp.getHeight()) * DEFAULT_GAME_HEIGHT);
        return new Cell(cellXPos, cellYPos);
    }

    // Update the window with the game info
    public void updateWindow() {
       gp.paintComponent(getGraphics());
    }

    /*
    MOUSE LISTENERS
     */

    @Override
    public void mouseClicked(@NotNull MouseEvent e) {
        Cell clickedCell = getCellFromPoint(e.getPoint());
        gp.gol.toggleCell(clickedCell);
        System.out.println(gp.gol.getAliveCells());
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
