package at.co.schwaerzler.maximilian;

import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class Utilities {
    @Nullable GameState centerGameStateOnBoard(GameState state, int boardSizeX, int boardSizeY) throws IllegalArgumentException {
        // if (state == null) return null;
        Rectangle stateBB = state.getBoundingBox();
        

        return new GameState();
    }
}
