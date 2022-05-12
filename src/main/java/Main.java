import core.*;

public class Main {

    // the starting point of the program should be separate,
    // we would create a formal starting point later
    public static void main(String[] args) {

//        System.out.println(FileManager.extractNameFromPath("../nam/achimsp8.txt"));

//        System.out.println(FileManager.FileReader.readPatternFromFile("achimsp4"));

        GameSetting setting = new GameSetting(BoardManager.GameType.COLONIES);
        setting.setKingPositions(
                new Position[] {
                        new Position(10, 10),
                        new Position(30, 15)
                }
        );

        BoardManager boardManager = new BoardManager(setting,50, 25);

//        for (int i = 0; i < boardManager.getBoard().getHeight(); ++i) {
//            for (int j = 0; j < boardManager.getBoard().getWidth(); ++j) {
//                if (Math.random() > 0.6)
//                    boardManager.makeAlive(new Position(j, i));
//            }
//        }


        for (int i = 0; i < boardManager.getBoard().getHeight(); ++i) {
            for (int j = 0; j < boardManager.getBoard().getWidth(); ++j) {
                int a = (int) (Math.random() * 5);
                if (boardManager.getBoard().getCellAt(new Position(j, i)) instanceof King) continue;
                if (a == 1)
//                    boardManager.makeAlive(new Worker(3), new Position(j, i));
                    boardManager.makeAlive(new Worker((int) (Math.random() * 2 + 1)), new Position(j, i));
            }
        }

        Renderer renderer = new Renderer();

        while (true) {
            Renderer.clearConsole();
            for (Colony colony : boardManager.getSetting().getColonies()) {
                System.out.printf("%f ", colony.getCoins());
            }
            System.out.printf("\n");
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