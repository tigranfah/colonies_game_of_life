import core.Board;
import core.BoardManager;
import core.Position;
import core.Renderer;
import gui.GameController;

public class Main {

    // the starting point of the program should be separate,
    // we would create a formal starting point later
    public static void main(String[] args) {
        BoardManager boardManager = new BoardManager(30, 30);

//        fillBoardFromFile(board, "src/main/resources/sample_board.json");

        for (int i = 0; i < boardManager.getBoard().getHeight(); ++i) {
            for (int j = 0; j < boardManager.getBoard().getWidth(); ++j) {
                if (Math.random() > 0.4)
                    boardManager.setAlive(new Position(j, i));
            }
        }

//        Renderer renderer = new Renderer();
        GameController game = new GameController(boardManager.getBoard());

        while (true) {
//            Renderer.clearConsole();
//            renderer.render(boardManager.getBoard());
            boardManager.step();

            game.updateBoard(boardManager.getBoard());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}