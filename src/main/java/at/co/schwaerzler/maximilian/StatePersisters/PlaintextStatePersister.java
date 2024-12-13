package at.co.schwaerzler.maximilian.StatePersisters;

import at.co.schwaerzler.maximilian.Cell;
import at.co.schwaerzler.maximilian.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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
    public GameState loadStateFromFile(Path file) throws IOException, IllegalArgumentException {
        // TODO
        return null;
    }
}
