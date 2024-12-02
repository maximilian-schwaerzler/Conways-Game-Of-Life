package at.co.schwaerzler.maximilian;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() throws HeadlessException {
        super();

        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("In headless environment. This is a game, what were you thinking?");
            System.exit(-1);
        }

        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Conway's Game of Life");
    }
}
