package at.co.schwaerzler.maximilian;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class GameOfLife {

    public HashSet<Cell> getAliveCells() {
        return aliveCells;
    }

    private HashSet<Cell> aliveCells = new HashSet<>();

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

    public void evolve() {
        HashSet<Cell> newState = new HashSet<>();
        HashSet<Cell> potRevival = new HashSet<>();
        for (Cell oldCell : aliveCells) {
            HashSet<Cell> neighbors = getNeighbors(oldCell.x, oldCell.y);
            int numNeighbors = 0;
            for (Cell neighbor : neighbors) {
                if (aliveCells.contains(neighbor)) {
                    numNeighbors++;
                } else {
                    potRevival.add(neighbor);
                }
            }

            // Any live cell with fewer than two live neighbours dies, as if by underpopulation.
            //noinspection StatementWithEmptyBody
            if (numNeighbors < 2) {
                // Cell dies, no copy
            }

            // Any live cell with two or three live neighbours lives on to the next generation.
            else if (numNeighbors == 2 || numNeighbors == 3) {
                newState.add(oldCell);
            }

            // Any live cell with more than three live neighbours dies, as if by overpopulation.
            else if (numNeighbors > 3) {
                // Cell dies, no copy
            }
        }

        for (Cell it : potRevival) {
            HashSet<Cell> neighbors = getNeighbors(it.x, it.y);
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
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (i == 1 && j == 1) {
                    continue;
                }

                neighbors.add(new Cell(x + i, y + j));
            }
        }

        return neighbors;
    }
}
