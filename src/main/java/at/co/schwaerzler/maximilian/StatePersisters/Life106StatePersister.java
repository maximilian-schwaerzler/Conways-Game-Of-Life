package at.co.schwaerzler.maximilian.StatePersisters;

import at.co.schwaerzler.maximilian.Cell;
import at.co.schwaerzler.maximilian.GameState;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

public class Life106StatePersister implements IStatePersister {
    private static final String FILE_HEADER = "#Life 1.06";

    @Override
    public void saveStateToFile(GameState state, Path file) throws IOException, IllegalArgumentException {
        if (state.isEmpty()) {
            throw new IllegalArgumentException("State is empty, not saving file");
        }

        ArrayList<String> lines = new ArrayList<>(state.size() + 1);
        lines.add(FILE_HEADER);
        for (Cell cell : state) {
            lines.add(cell.x() + " " + cell.y());
        }

        Files.write(file, lines);
    }

    @Override
    public GameState loadStateFromFile(Path file) throws IOException, IllegalArgumentException {
        if (Files.notExists(file)) {
            throw new IllegalArgumentException("The file path does not exist: " + file.toAbsolutePath());
        }

        GameState newState = new GameState();

        try (BufferedReader bufferedReader = Files.newBufferedReader(file)) {
            String header = bufferedReader.readLine();
            if (!Objects.equals(header, FILE_HEADER)) {
                throw new IllegalArgumentException("The file is not a valid Life 1.06 file");
            }

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) continue;
                String[] coords = line.split(" ");
                if (coords.length != 2) {
                    throw new IOException("Error in file format, aborting");
                }

                try {
                    newState.add(new Cell(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
                } catch (NumberFormatException e) {
                    throw new IOException("Error reading file: " + e.getMessage());
                }
            }
        }

        if (newState.isEmpty()) {
            throw new IOException("File was empty");
        }

        return newState;
    }
}
