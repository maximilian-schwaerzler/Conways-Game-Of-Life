package at.co.schwaerzler.maximilian;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;

public interface IStatePersister {
    String[] LIFE_106_FILE_EXT = {"life", "lif"};

    void saveStateToFile(HashSet<Cell> state, Path file) throws IOException, IllegalArgumentException;

    HashSet<Cell> loadStateFromFile(Path file) throws IOException, IllegalArgumentException;

    static IStatePersister getPersisterForFile(Path file) throws IllegalArgumentException {
        String ext = FilenameUtils.getExtension(file.getFileName().toString());
        if (Arrays.asList(LIFE_106_FILE_EXT).contains(ext)) {
            return new Life106StatePersister();
        }

        throw new IllegalArgumentException("Couldn't match File Loader from extension: " + ext);
    }
}
