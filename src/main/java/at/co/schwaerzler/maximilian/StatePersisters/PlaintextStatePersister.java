package at.co.schwaerzler.maximilian.StatePersisters;

import at.co.schwaerzler.maximilian.GameState;

import java.io.IOException;
import java.nio.file.Path;

public class PlaintextStatePersister implements IStatePersister {
    @Override
    public void saveStateToFile(GameState state, Path file) throws IOException, IllegalArgumentException {
        // TODO
    }

    @Override
    public GameState loadStateFromFile(Path file) throws IOException, IllegalArgumentException {
        // TODO
        return null;
    }
}
