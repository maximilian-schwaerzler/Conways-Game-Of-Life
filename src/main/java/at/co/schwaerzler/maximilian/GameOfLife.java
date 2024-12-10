package at.co.schwaerzler.maximilian;

import org.jetbrains.annotations.NotNull;

/**
 * The actual game logic.
 */
public class GameOfLife {
    private GameState aliveCells = new GameState();

    private static @NotNull GameState getNeighbors(int x, int y) {
        GameState neighbors = new GameState();
        for (int j = -1; j < 2; j++) {
            for (int i = -1; i < 2; i++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                neighbors.add(new Cell(x + i, y + j));
            }
        }

        return neighbors;
    }

    public void loadState(GameState state) {
        aliveCells = state;
    }

    public GameState getAliveCells() {
        return aliveCells;
    }

    public boolean isAlive(int x, int y) {
        return aliveCells.contains(new Cell(x, y));
    }

    public void toggleCell(int x, int y) {
        if (isAlive(x, y)) {
            aliveCells.remove(new Cell(x, y));
        } else {
            aliveCells.add(new Cell(x, y));
        }
    }

    public void toggleCell(@NotNull Cell cell) {
        toggleCell(cell.x(), cell.y());
    }

    public void resetGame() {
        aliveCells.clear();
    }

    public void evolve() {
        GameState newState = new GameState();
        GameState potRevival = new GameState();
        for (Cell oldCell : aliveCells) {
            GameState neighbors = getNeighbors(oldCell.x(), oldCell.y());
            int numNeighbors = 0;
            for (Cell neighbor : neighbors) {
                if (aliveCells.contains(neighbor)) {
                    numNeighbors++;
                } else {
                    potRevival.add(neighbor);
                }
            }

            // Any live cell with two or three live neighbours lives on to the next generation.
            if (numNeighbors == 2 || numNeighbors == 3) {
                newState.add(oldCell);
            }
        }

        for (Cell it : potRevival) {
            GameState neighbors = getNeighbors(it.x(), it.y());
            int numNeighbors = 0;
            for (Cell neighbor : neighbors) {
                if (aliveCells.contains(neighbor)) {
                    numNeighbors++;
                }
            }

            // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction
            if (numNeighbors == 3) {
                newState.add(it);
            }
        }

        aliveCells = newState;
    }
}
