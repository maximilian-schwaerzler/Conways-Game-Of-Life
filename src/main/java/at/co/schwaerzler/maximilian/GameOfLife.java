package at.co.schwaerzler.maximilian;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class GameOfLife {
    private HashSet<Cell> aliveCells = new HashSet<>();

    public void loadState(HashSet<Cell> state) {
        aliveCells = state;
    }

    public HashSet<Cell> getAliveCells() {
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
        HashSet<Cell> newState = new HashSet<>();
        HashSet<Cell> potRevival = new HashSet<>();
        for (Cell oldCell : aliveCells) {
            HashSet<Cell> neighbors = getNeighbors(oldCell.x(), oldCell.y());
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
            HashSet<Cell> neighbors = getNeighbors(it.x(), it.y());
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

    private static @NotNull HashSet<Cell> getNeighbors(int x, int y) {
        HashSet<Cell> neighbors = new HashSet<>();
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
}
