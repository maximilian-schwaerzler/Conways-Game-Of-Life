package at.co.schwaerzler.maximilian;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;

public interface IStatePersister {
    void saveStateToFile(HashSet<Cell> state, Path file) throws IOException, IllegalArgumentException;

    HashSet<Cell> loadStateFromFile(Path file) throws IOException, IllegalArgumentException;
}
