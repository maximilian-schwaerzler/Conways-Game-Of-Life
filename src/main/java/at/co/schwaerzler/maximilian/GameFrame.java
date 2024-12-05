package at.co.schwaerzler.maximilian;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame(int gameSize, int windowSize) throws HeadlessException {
        super();

        if (GraphicsEnvironment.isHeadless()) {
            throw new HeadlessException("In headless environment. This is a game, what were you thinking?");
        }

        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(100, 100));
        setResizable(false);
        GamePanel gp = new GamePanel(gameSize, windowSize);
        add(gp);
        pack();
        setLocationRelativeTo(null);
    }
}
