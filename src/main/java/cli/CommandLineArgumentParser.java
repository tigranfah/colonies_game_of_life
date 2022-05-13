package cli;

import core.BoardManager;
import core.GameSetting;
import core.Position;
import exceptions.InvalidCommandLineArgumentException;

public class CommandLineArgumentParser {

    private static int i = 0;

    public static String nextToken(String[] args) {
        if (i < args.length)
            return args[i++];
        return null;
    }

    public static BoardManager parseArgumentsToBoardManager(String[] args) throws InvalidCommandLineArgumentException {
        if (args == null)
            throw new InvalidCommandLineArgumentException();

        String widthStr = nextToken(args);
        if (widthStr == null)
            throw new InvalidCommandLineArgumentException();
        String heightStr = nextToken(args);
        if (heightStr == null)
            throw new InvalidCommandLineArgumentException();

        int width = Integer.valueOf(widthStr), height = Integer.valueOf(heightStr);
        GameSetting setting = new GameSetting(BoardManager.GameType.COLONIES);
        System.out.println("Width: " + width + ", Height: " + height);
        String colNumberStr = nextToken(args);
        int numberOfColonies = 2;
        if (colNumberStr != null) {
            numberOfColonies = Integer.valueOf(colNumberStr);
        }
        System.out.println("Number of colonies: " + numberOfColonies);

        Position[] kingPositions = new Position[numberOfColonies];
        for (int i = 0; i < kingPositions.length; ++i) {
            kingPositions[i] = Position.generateRandomly(0, width, 0, height - 5);
        }

        setting.setKingPositions(kingPositions);

        String guiOnStr = nextToken(args);
        if (guiOnStr != null && guiOnStr.equals("gui"))
            setting.setGuiOn(true);

        return new BoardManager(setting, width, height);
    }

}