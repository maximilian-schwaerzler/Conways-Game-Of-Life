package at.co.schwaerzler.maximilian;

import java.awt.*;
import java.util.HashSet;

/**
 * A custom hash set for holding the game state
 */
public class GameState extends HashSet<Cell> {
    public GameState offset(int deltaX, int deltaY) {
        GameState newState = new GameState();
        for (Cell cell : this) {
            newState.add(new Cell(cell.x() + deltaX, cell.y() + deltaY));
        }

        return newState;
    }

    public Rectangle getBoundingBox() {
        // TODO
        return new Rectangle();
    }
}
