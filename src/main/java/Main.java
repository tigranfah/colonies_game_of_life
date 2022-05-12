import core.*;
import io.FileManager;
import utils.Matrix;

public class Main {

    // the starting point of the program should be separate,
    // we would create a formal starting point later
    public static void main(String[] args) {
        BoardManager boardManager = new BoardManager(BoardManager.GameType.COLONIES,45, 20);

//        for (int i = 0; i < boardManager.getBoard().getHeight(); ++i) {
//            for (int j = 0; j < boardManager.getBoard().getWidth(); ++j) {
//                if (Math.random() > 0.6)
//                    boardManager.makeAlive(new Position(j, i));
//            }
//        }


        for (int i = 0; i < boardManager.getBoard().getHeight(); ++i) {
            for (int j = 0; j < boardManager.getBoard().getWidth(); ++j) {
                int a = (int) (Math.random() * 5);
                if (a == 1)
//                    boardManager.makeAlive(new Worker(3), new Position(j, i));
                    boardManager.makeAlive(new Worker((int) (Math.random() * 2 + 1)), new Position(j, i));
                if (a == 2)
                    boardManager.makeAlive(new Fighter((int) (Math.random() * 2 + 1)), new Position(j, i));
            }
        }

        Renderer renderer = new Renderer();

        while (true) {
            Renderer.clearConsole();
            renderer.render(boardManager.getBoard());
            System.out.printf("\n");
            boardManager.step();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}