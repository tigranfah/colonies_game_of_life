
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

//        for (int i = 0; i < boardManager.getBoard().getHeight(); ++i) {
//            for (int j = 0; j < boardManager.getBoard().getWidth(); ++j) {
//                if (Math.random() > 0.6)
//                    boardManager.makeAlive(new Position(j, i));
//            }
//        }

//        System.out.println(boardManager.getSetting().getColony(1));

        Pattern pat = boardManager.getSetting().getColony(1).getStrategy().generatePattern();
        Matrix<Worker> mat = pat.toWorkerMatrix(1);
//        System.out.println(mat);
//        System.out.println(mat.get(0, 0).getClass());
//
//        boardManager.getBoard().setBoardSubpart(mat, new Position(5, 5), true);

//        for (int i = 0; i < boardManager.getBoard().getHeight(); ++i) {
//            for (int j = 0; j < boardManager.getBoard().getWidth(); ++j) {
//                int a = (int) (Math.random() * 5);
//                if (boardManager.getBoard().getCellAt(new Position(j, i)) instanceof King) continue;
//                if (a == 1)
////                    boardManager.makeAlive(new Worker(3), new Position(j, i));
//                    boardManager.makeAlive(new Worker((int) (Math.random() * 2 + 1)), new Position(j, i));
//            }
//        }

//        Renderer renderer = new Renderer();
        GameController g = new GameController(boardManager.getBoard());


        while (true) {
            for (Colony colony : boardManager.getSetting().getColonies()) {
                System.out.printf("%f ", colony.getCoins());
            }

            g.updateBoard(boardManager.getBoard());
            boardManager.step();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}