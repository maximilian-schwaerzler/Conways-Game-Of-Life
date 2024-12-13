package at.co.schwaerzler.maximilian.StatePersisters;

import at.co.schwaerzler.maximilian.GameState;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import static at.co.schwaerzler.maximilian.ApplicationConstants.LIFE_106_FILE_EXT;
import static at.co.schwaerzler.maximilian.ApplicationConstants.PLAINTEXT_FILE_EXT;

/**
 * An interface for all kinds of state persisters, a.k.a. saving and loading/parsing of state files.
 */
public interface IStatePersister {

    /**
     * Matches the right state persister to the file according to the file extension.
     *
     * @return The right IStatePersister or null if no persister could be found.
     */
    static @Nullable IStatePersister getPersisterForFileExtension(@NotNull Path file) {
        final Logger LOGGER = LogManager.getLogger();
        String ext = FilenameUtils.getExtension(file.getFileName().toString()).toLowerCase();
        LOGGER.debug("File extension: {}", ext);
        if (Arrays.asList(LIFE_106_FILE_EXT).contains(ext)) {
            return new Life106StatePersister();
        } else if (Arrays.asList(PLAINTEXT_FILE_EXT).contains(ext)) {
            return new PlaintextStatePersister();
        }

        return null;
    }

    /**
     * Saves the current game state to a file
     *
     * @param state The current game state
     * @param file  The file to save the state to
     * @throws IOException              What it says
     * @throws IllegalArgumentException Same
     */
    void saveStateToFile(GameState state, Path file) throws IOException, IllegalArgumentException;

    /**
     * Loads the game state from a file
     *
     * @param file The file to load from
     * @return The new game state
     * @throws IOException              e.g. error when opening the file
     * @throws IllegalArgumentException e.g. when it's the wrong file type
     */
    @Nullable GameState loadStateFromFile(Path file) throws IOException, IllegalArgumentException;
}
