import cli.CommandLineArgumentParser;
import core.*;
import exceptions.InvalidCommandLineArgumentException;
import gui.GameController;
import utils.Matrix;
import utils.Pattern;

public class Main {

    public static void main(String[] args) throws InvalidCommandLineArgumentException {

//        GameSetting setting = new GameSetting(BoardManager.GameType.COLONIES);
//        setting.setKingPositions(
//                new Position[] {
//                        new Position(10, 10),
//                        new Position(30, 45),
//                        new Position(65, 65),
//                }
//        );
//
//        BoardManager boardManager = new BoardManager(setting,80, 80);

        BoardManager boardManager = CommandLineArgumentParser.parseArgumentsToBoardManager(args);

        Renderer renderer = new Renderer();

        if (boardManager.getSetting().isGuiOn()) {
            GameController g = new GameController(boardManager);
            g.run();
        } else {

            while (true) {
                for (Colony col : boardManager.getSetting().getColonies())
                    System.out.printf("Colony %d: coints %f \n", col.getColonyIndex(), col.getCoins());

                renderer.render(boardManager.getBoard());
                boardManager.step();

                try {
                    Thread.sleep(1000 / (GameSetting.GENERATIONS_PER_SECOND));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if (boardManager.isGameOver() != -1) {
                    System.out.printf(
                            "Game is over. Won the colony %d.\n",
                            boardManager.getSetting().getColonies().get(0).getColonyIndex()
                    );
                    System.exit(0);
                }
            }

        }

    }

}