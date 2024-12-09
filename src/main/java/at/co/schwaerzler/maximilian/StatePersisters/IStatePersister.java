package at.co.schwaerzler.maximilian.StatePersisters;

import at.co.schwaerzler.maximilian.GameState;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import static at.co.schwaerzler.maximilian.ApplicationConstants.LIFE_106_FILE_EXT;

public interface IStatePersister {

    /**
     * Matches the right state persister to the file according to the file extension.
     *
     * @return The right IStatePersister or null if no persister could be found.
     */
    static @Nullable IStatePersister getPersisterForFileExtension(@NotNull Path file) {
        String ext = FilenameUtils.getExtension(file.getFileName().toString()).toLowerCase();
        if (Arrays.asList(LIFE_106_FILE_EXT).contains(ext)) {
            return new Life106StatePersister();
        }

        return null;
    }

    void saveStateToFile(GameState state, Path file) throws IOException, IllegalArgumentException;

    GameState loadStateFromFile(Path file) throws IOException, IllegalArgumentException;
}
