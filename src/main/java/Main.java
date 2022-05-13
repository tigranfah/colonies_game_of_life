import cli.CommandLineArgumentParser;
import core.*;
import exceptions.InvalidCommandLineArgumentException;
import gui.GameController;
import utils.Matrix;
import utils.Pattern;

public class Main {

    public static void main(String[] args) throws InvalidCommandLineArgumentException {

        BoardManager boardManager = CommandLineArgumentParser.parseArgumentsToBoardManager(args);

        Renderer renderer = new Renderer();

        if (boardManager.getSetting().isGuiOn()) {
            GameController g = new GameController(boardManager);
            g.run();
        } else {

            while (true) {
                for (Colony col : boardManager.getSetting().getColonies())
                    System.out.printf("Colony %d: coins %f \n", col.getColonyIndex(), col.getCoins());

                renderer.render(boardManager.getBoard());
                boardManager.step();

                if (boardManager.isGameOver() != -1) {
                    System.out.printf(
                            "Game is over. Won the colony %d.\n",
                            boardManager.isGameOver()
                    );
                    System.exit(0);
                }

                try {
                    Thread.sleep(1000 / (GameSetting.GENERATIONS_PER_SECOND));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        }

    }

}