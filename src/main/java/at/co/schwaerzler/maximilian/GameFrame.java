package at.co.schwaerzler.maximilian;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameFrame extends JFrame implements MouseListener, KeyListener {
    public GameFrame() throws HeadlessException {
        super();

        if (GraphicsEnvironment.isHeadless()) {
//            System.err.println("In headless environment. This is a game, what were you thinking?");
            throw new HeadlessException("In headless environment. This is a game, what were you thinking?");
        }

        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Conway's Game of Life");
        addMouseListener(this);
        addKeyListener(this);
    }

    @Override
    public void mouseClicked(@NotNull MouseEvent e) {
        System.out.println(e.getPoint());
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
