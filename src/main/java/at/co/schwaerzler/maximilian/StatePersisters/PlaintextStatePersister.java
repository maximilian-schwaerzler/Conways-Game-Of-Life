package at.co.schwaerzler.maximilian.StatePersisters;

import at.co.schwaerzler.maximilian.Cell;
import at.co.schwaerzler.maximilian.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * A state persister for the <a href="https://conwaylife.com/wiki/Plaintext">Plaintext</a> file format
 */
public class PlaintextStatePersister implements IStatePersister {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void saveStateToFile(@NotNull GameState state, Path file) throws IOException, IllegalArgumentException {
        if (state.isEmpty()) {
//            throw new IllegalArgumentException("State is empty, not saving file");
            LOGGER.warn("State is empty, not saving file");
        }

        Rectangle bb = state.getBoundingBox();
        LOGGER.debug(bb);
        Point bbLocation = bb.getLocation();
        ArrayList<String> lines = new ArrayList<>(bb.height);
        for (int lineOffset = 0; lineOffset < bb.height; lineOffset++) {
            StringBuilder lineBuilder = new StringBuilder();
            for (int colOffset = 0; colOffset < bb.width; colOffset++) {
                if (state.contains(new Cell(bbLocation.x + colOffset, bbLocation.y + lineOffset))) {
                    lineBuilder.append("O");
                } else {
                    lineBuilder.append(".");
                }
            }
            lines.add(lineBuilder.toString());
        }

        Files.write(file, lines);
    }

    @Override
    public @Nullable GameState loadStateFromFile(Path file) throws IOException, IllegalArgumentException {
        if (Files.notExists(file)) {
            throw new IllegalArgumentException("The file path does not exist: " + file.toAbsolutePath());
        }

        GameState newState = new GameState();

        try (BufferedReader bufferedReader = Files.newBufferedReader(file)) {
            String line;
            int lineCounter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                // Ignore comments
                if (line.startsWith("!")) continue;

                // Lines with only dead cells are sometimes left blank
                if (line.isBlank()) {
                    lineCounter++;
                    continue;
                }

                int colCounter = 0;
                for (char c : line.toCharArray()) {
                    if (c == 'O') {
                        newState.add(new Cell(colCounter, lineCounter));
                    }

                    colCounter++;
                }

                lineCounter++;
            }
        }

        return newState;
    }
}
