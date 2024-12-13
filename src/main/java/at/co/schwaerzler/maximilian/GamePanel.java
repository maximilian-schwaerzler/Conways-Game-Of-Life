package at.co.schwaerzler.maximilian;

import at.co.schwaerzler.maximilian.StatePersisters.IStatePersister;
import at.co.schwaerzler.maximilian.StatePersisters.Life106StatePersister;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static at.co.schwaerzler.maximilian.ApplicationConstants.DEFAULT_FILE_EXT;
import static at.co.schwaerzler.maximilian.ApplicationConstants.LIFE_106_FILE_EXT;

/**
 * The panel that renders the actual game to the window.
 * It is also responsible for user input.
 */
public class GamePanel extends JPanel implements MouseListener, KeyListener {
    private static final Logger LOGGER = LogManager.getLogger();
    private final int windowSize;
    private final int gameSize;
    private final int cellSize;
    private final Timer gameTickTimer;
    public GameOfLife gol;
    private boolean isPaused = true;

    public GamePanel(int gameSize, int windowSize, @Nullable GameState initialState) {
        setBackground(Color.BLACK);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        this.windowSize = windowSize;
        this.gameSize = gameSize;
        cellSize = windowSize / this.gameSize;
        gol = new GameOfLife();
        if (initialState != null) {
            // gol.loadState(initialState.offset(10, 10));
            repaint();
        }

        gameTickTimer = new Timer(50, _ -> gameTick());
        gameTickTimer.setInitialDelay(0);
        setIsPaused(isPaused);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(windowSize, windowSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (Cell cell : gol.getAliveCells()) {
            g.fillRect(cell.x() * cellSize, cell.y() * cellSize, cellSize, cellSize);
        }
    }

    public Cell getCellFromPoint(@NotNull Point p) {
        int cellXPos = (int) (((double) p.x / (double) getWidth()) * gameSize);
        int cellYPos = (int) (((double) p.y / (double) getHeight()) * gameSize);
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

    private void setIsPaused(boolean value) {
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
            updateWindow();
        } else if (keyCode == KeyEvent.VK_S && e.getModifiersEx() == InputEvent.CTRL_DOWN_MASK && isPaused) {
            saveCurrentState();
        }
    }

    private void saveCurrentState() {
        LOGGER.debug("Saving current state");
        GameState currentState = gol.getAliveCells();
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

//        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter life106Filter = new FileNameExtensionFilter("Life 1.06", LIFE_106_FILE_EXT);
        fc.addChoosableFileFilter(life106Filter);

        fc.setFileFilter(life106Filter);
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            FileFilter usedFileFilter = fc.getFileFilter();
            Path selectedFilePath;
            if (FilenameUtils.getExtension(file.getName()).isEmpty()) {
                if (usedFileFilter instanceof FileNameExtensionFilter) {
                    selectedFilePath = Path.of(file.getAbsolutePath() + "." + ((FileNameExtensionFilter) usedFileFilter).getExtensions()[0]);
                } else {
                    selectedFilePath = Path.of(file.getAbsolutePath() + "." + DEFAULT_FILE_EXT);
                }
            } else {
                selectedFilePath = file.toPath();
            }

            IStatePersister statePersister = IStatePersister.getPersisterForFileExtension(selectedFilePath);
            if (statePersister == null) {
                statePersister = new Life106StatePersister();
            }

            try {
                statePersister.saveStateToFile(currentState, selectedFilePath);
//                System.out.println("Saved state to: " + selectedFilePath);
                LOGGER.info("Saved state to: {}", selectedFilePath);
            } catch (IOException e) {
//                System.err.println("Failed to save the current state to a file: " + e.getMessage());
                LOGGER.error("Failed to save the current state to a file", e);
            }
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
