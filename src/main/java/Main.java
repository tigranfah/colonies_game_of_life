import core.*;
import gui.GameController;
import utils.Matrix;
import utils.Pattern;

public class Main {

    public static void main(String[] args) {

        GameSetting setting = new GameSetting(BoardManager.GameType.COLONIES);
        setting.setKingPositions(
                new Position[] {
                        new Position(10, 10),
                        new Position(30, 45),
                        new Position(65, 65),
                }
        );

        BoardManager boardManager = new BoardManager(setting,80, 80);

        Pattern pat = boardManager.getSetting().getColony(1).getStrategy().generatePattern();
        Matrix<Worker> mat = pat.toWorkerMatrix(1);
        GameController g = new GameController(boardManager);

        g.run();
    }

}