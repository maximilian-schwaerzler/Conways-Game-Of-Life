package at.co.schwaerzler.maximilian;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;

/**
 * A custom hash set of cells for holding the game state
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
        int[] xCoords = this.stream().mapToInt(Cell::x).toArray();
        int[] yCoords = this.stream().mapToInt(Cell::y).toArray();
        int minX = Arrays.stream(xCoords).min().orElse(0);
        int maxX = Arrays.stream(xCoords).max().orElse(0);
        int minY = Arrays.stream(yCoords).min().orElse(0);
        int maxY = Arrays.stream(yCoords).max().orElse(0);

        return new Rectangle(minX, minY, maxX, maxY);
    }
}
