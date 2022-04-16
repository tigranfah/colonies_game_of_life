import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import core.Board;
import core.Position;
import core.Renderer;
import exceptions.InvalidPosition;
import org.json.JSONObject;

import utils.Utils;

public class Main {

    public static void fillBoardFromFile(Board board, String filePath) {
        Position[] positions = null;
        try {
            positions = parseJsonFileToPositions(board, filePath);
        } catch (InvalidPosition e) {
            System.out.println("In the file " + filePath + " is given an invalid position.");
            return;
        }

        for (Position pos : positions) {
            board.setAlive(pos);
        }

    }

    public static Position[] parseJsonFileToPositions(Board board, String filePath) throws InvalidPosition {
        FileInputStream file = null;
        String content = "";
        try {
            file = new FileInputStream(filePath); // "src/main/resources/sample_board.json"
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())
                content += sc.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                System.out.println("Error while trying to close " + filePath);
            }
        }

        Position[] positions = new Position[board.getWidth() * board.getHeight()];
        int count = 0;

        JSONObject jsonFile = new JSONObject(content);
        for (Object obj : jsonFile.getJSONArray("alive_cells")) {
            JSONObject jsonPos = (JSONObject) obj;
            Position pos = new Position((int) jsonPos.get("x"), (int) jsonPos.get("y"));
            if (!board.isValidBoardPosition(pos))
                throw new InvalidPosition(pos + "is not valid.");
            positions[count++] = pos;
        }

        return Utils.resizePartiallyFilledPositionArray(positions, count);
    }

    // the starting point of the program should be separate,
    // we would create a formal starting point later
    public static void main(String[] args) {
        Board board = new Board(20, 20);

//        fillBoardFromFile(board, "src/main/resources/sample_board.json");

        for (int i = 0; i < board.getHeight(); ++i) {
            for (int j = 0; j < board.getWidth(); ++j) {
                if (Math.random() > 0.4)
                    board.setAlive(new Position(j, i));
            }
        }

        Renderer renderer = new Renderer();

        while (true) {
            renderer.render(board);
            System.out.printf("\n");
            board.step();

            try {
                Thread.sleep(100);
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