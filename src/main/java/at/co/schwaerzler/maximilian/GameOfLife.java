package at.co.schwaerzler.maximilian;

import java.util.HashSet;

public class GameOfLife {
    static final int GAME_FIELD_WIDTH = 50;
    static final int GAME_FIELD_HEIGHT = 50;

    private HashSet<Cell> aliveCells = new HashSet<>();

    public GameOfLife() {

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

    public void evolve() {
        HashSet<Cell> newState = new HashSet<>(aliveCells);
        for (Cell oldCell : aliveCells) {
            HashSet<Cell> neighbors = new HashSet<>();
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    if (x == 1 && y == 1) {
                        continue;
                    }

                    Cell potNeighbor = new Cell(x, y);
                    if (aliveCells.contains(potNeighbor)) {
                        neighbors.add(potNeighbor);
                    }
                }
            }

            final int numNeighbors = neighbors.size();

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
    }
}
