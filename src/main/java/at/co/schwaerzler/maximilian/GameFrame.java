package at.co.schwaerzler.maximilian;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public static final int DEFAULT_GAME_WIDTH = 50;
    public static final int DEFAULT_GAME_HEIGHT = 50;
    public static final int DEFAULT_WINDOW_WIDTH = 500;
    public static final int DEFAULT_WINDOW_HEIGHT = 500;

    public GameFrame() throws HeadlessException {
        super();

        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException("In headless environment. This is a game, what were you thinking?");
        }

        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(100, 100));
        setResizable(false);
        GamePanel gp = new GamePanel(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT,
                DEFAULT_GAME_WIDTH, DEFAULT_GAME_HEIGHT);
        add(gp);
        pack();
    }
}
