package at.co.schwaerzler.maximilian;

import org.apache.commons.cli.*;

public class Main {
    public static final int DEFAULT_GAME_SIZE = 50;
    public static final int DEFAULT_WINDOW_SIZE = 800;

    public static final String GAME_SIZE_ARG_NAME = "game-size";
    public static final String WINDOW_SIZE_ARG_NAME = "window-size";
    public static final String SHORT_HELP_ARG_NAME = "h";
    public static final String LONG_HELP_ARG_NAME = "help";

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();

        Options options = new Options();
        Option gameSizeOpt = Option.builder(GAME_SIZE_ARG_NAME)
                .hasArg(true)
//                .optionalArg(true)
                .desc("square size of the game field")
                .type(Integer.class)
                .build();
        Option windowSizeOpt = Option.builder(WINDOW_SIZE_ARG_NAME)
                .hasArg(true)
//                .optionalArg(true)
                .desc("square size of the window")
                .type(Integer.class)
                .build();


        options.addOption(gameSizeOpt);
        options.addOption(windowSizeOpt);
        options.addOption(SHORT_HELP_ARG_NAME, LONG_HELP_ARG_NAME, false, "show this help");

        int gameSize = DEFAULT_GAME_SIZE;
        int windowSize = DEFAULT_WINDOW_SIZE;

        try {
            CommandLine line = parser.parse(options, args);

            if (line.hasOption(SHORT_HELP_ARG_NAME) || line.hasOption(LONG_HELP_ARG_NAME)) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("Conways-Game-Of-Life.jar", options);
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

            GameFrame gf = new GameFrame(gameSize, windowSize);
            gf.setVisible(true);
        } catch (ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
        }
    }
}