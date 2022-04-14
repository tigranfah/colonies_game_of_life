public class Main {

    // the starting point of the program should be separate,
    // we would create a formal starting point later
    public static void main(String[] args) {
        Board testBoard = new Board(10, 10);

        testBoard.setAlive(new Position(2, 2));
        testBoard.setAlive(new Position(2, 3));
        testBoard.setAlive(new Position(2, 4));

        Renderer renderer = new Renderer();

        while (true) {
            renderer.render(testBoard);
            testBoard.step();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // testBoard.setAlive(2, 2);
        // testBoard.setAlive(2, 3);
        // testBoard.setAlive(2, 4);
        // testBoard.printBoard();
        // System.out.println(testBoard.numberOfAliveNeighbours(2, 3));
        // while (true) {
//             testBoard.step();
//             testBoard.printBoard();
//             try {
//                 // delay every step with n milliseconds to not burn your CPU
//                 Thread.sleep(500);
//             } catch(InterruptedException ex)
//             {
//                 Thread.currentThread().interrupt();
//             }
// //            testBoard.step();
// //            testBoard.printBoard();
//         }
    }

}