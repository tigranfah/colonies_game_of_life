import core.Board;
import core.Renderer;

public class Main {

    // the starting point of the program should be separate,
    // we would create a formal starting point later
    public static void main(String[] args) {
//        Board board = new Board(30, 30);
        Board board = new Board("src/main/resources/sample_board.json");

//        fillBoardFromFile(board, "src/main/resources/sample_board.json");

//        for (int i = 0; i < board.getHeight(); ++i) {
//            for (int j = 0; j < board.getWidth(); ++j) {
//                if (Math.random() > 0.4)
//                    board.setAlive(new Position(j, i));
//            }
//        }

        Renderer renderer = new Renderer();

        while (true) {
            Renderer.clearConsole();
            renderer.render(board);
            System.out.printf("\n");
            board.step();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}