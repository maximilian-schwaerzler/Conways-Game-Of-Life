package at.co.schwaerzler.maximilian;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public static final int DEFAULT_GAME_WIDTH = 10;
    public static final int DEFAULT_GAME_HEIGHT = 10;
    public static final int DEFAULT_WINDOW_WIDTH = 500;
    public static final int DEFAULT_WINDOW_HEIGHT = 500;

    private final GamePanel gp;

    public GameFrame() throws HeadlessException {
        super();

        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException("In headless environment. This is a game, what were you thinking?");
        }

        gp = new GamePanel(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT, DEFAULT_GAME_WIDTH, DEFAULT_GAME_HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conway's Game of Life");
        setResizable(false);
        addMouseListener(gp);
        addKeyListener(gp);
        getContentPane().add(gp);
        pack();
    }
}
