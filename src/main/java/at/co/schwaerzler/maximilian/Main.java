package at.co.schwaerzler.maximilian;

import at.co.schwaerzler.maximilian.StatePersisters.IStatePersister;
import org.apache.commons.cli.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;

import static at.co.schwaerzler.maximilian.ApplicationConstants.*;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * The entry function to the game. Parse the arguments and maybe load the initial state.
     *
     * @param args The command line arguments. Very important, do not delete.
     */
    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();

        Options options = new Options();
        Option gameSizeOpt = Option.builder()
                .longOpt(GAME_SIZE_ARG_NAME)
                .hasArg(true)
                .desc("square size of the game field")
                .type(Integer.class)
                .build();
        Option windowSizeOpt = Option.builder()
                .longOpt(WINDOW_SIZE_ARG_NAME)
                .hasArg(true)
                .desc("square size of the window")
                .type(Integer.class)
                .build();

        Option stateFileOpt = Option.builder(STATE_FILE_ARG_NAME)
                .hasArg(true)
                .desc("a state file from which the initial state of the game will be set")
                .type(Path.class)
                .build();


        options.addOption(SHORT_HELP_ARG_NAME, LONG_HELP_ARG_NAME, false, "show this help");
        options.addOption(stateFileOpt);
        options.addOption(gameSizeOpt);
        options.addOption(windowSizeOpt);

        int gameSize = DEFAULT_GAME_SIZE;
        int windowSize = DEFAULT_WINDOW_SIZE;

        try {
            CommandLine line = parser.parse(options, args);

            if (line.hasOption(SHORT_HELP_ARG_NAME) || line.hasOption(LONG_HELP_ARG_NAME)) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("GameOfLife.jar", options);
                return;
            }

            if (line.hasOption(GAME_SIZE_ARG_NAME)) {
                int gameSizeInput = line.getParsedOptionValue(GAME_SIZE_ARG_NAME);
                if (gameSizeInput > 0 && gameSizeInput <= 100) {
                    gameSize = gameSizeInput;
                }
            }

            if (line.hasOption(WINDOW_SIZE_ARG_NAME)) {
                int windowSizeInput = line.getParsedOptionValue(WINDOW_SIZE_ARG_NAME);
                if (windowSizeInput >= 200 && windowSizeInput <= 1000) {
                    windowSize = windowSizeInput;
                }
            }

            GameState initialState = null;

            if (line.hasOption(STATE_FILE_ARG_NAME)) {
                Path stateFile = line.getParsedOptionValue(STATE_FILE_ARG_NAME);
                try {
                    IStatePersister loader = IStatePersister.getPersisterForFileExtension(stateFile);
                    if (loader != null) {
                        initialState = loader.loadStateFromFile(stateFile);
                    } else {
                        LOGGER.error("Could not find the right loader for this file. Did you use the right file extension? (Parsed extension: {})", FilenameUtils.getExtension(stateFile.toString()));
                    }
                } catch (IllegalArgumentException e) {
                    LOGGER.error("Error loading file '{}'", stateFile, e);
                } catch (IOException e) {
                    LOGGER.error("Error reading file '{}'", stateFile, e);

                }
            }

            int finalGameSize = gameSize;
            int finalWindowSize = windowSize;
            GameState finalInitialState = initialState;
            SwingUtilities.invokeLater(() -> {
                GameFrame gf = new GameFrame(finalGameSize, finalWindowSize, finalInitialState);
                gf.setVisible(true);
            });
        } catch (ParseException e) {
            LOGGER.error("Parsing of state file failed", e);
        }
    }
}