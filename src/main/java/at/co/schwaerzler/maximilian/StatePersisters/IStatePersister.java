package at.co.schwaerzler.maximilian.StatePersisters;

import at.co.schwaerzler.maximilian.GameState;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public interface IStatePersister {
    String[] LIFE_106_FILE_EXT = {"life", "lif"};

    static IStatePersister getPersisterForFile(Path file) throws IllegalArgumentException {
        String ext = FilenameUtils.getExtension(file.getFileName().toString()).toLowerCase();
        if (Arrays.asList(LIFE_106_FILE_EXT).contains(ext)) {
            return new Life106StatePersister();
        }

        throw new IllegalArgumentException("Couldn't match File Loader from extension: " + ext);
    }

    void saveStateToFile(GameState state, Path file) throws IOException, IllegalArgumentException;

    GameState loadStateFromFile(Path file) throws IOException, IllegalArgumentException;
}
