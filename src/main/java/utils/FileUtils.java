package utils;

import core.Board;
import core.Position;
import exceptions.InvalidPosition;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {

    public static JSONObject parseJsonFile(String filePath) {
        FileInputStream file = null;
        String content = "";
        try {
            file = new FileInputStream(filePath); // "src/main/resources/sample_board.json"
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())
                content += sc.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println(filePath + ": File not found");
        } finally {
            try {
                if (file != null)
                    file.close();
            } catch (IOException e) {
                System.out.println("Error while trying to close the file : " + filePath);
            }
        }

        if (content.equals(""))
            return null;

        return new JSONObject(content);
    }

    public static void initializeBoardFromJsonObject(Board board, JSONObject jsonObj) {

        int width = (int) jsonObj.get("width");
        int height = (int) jsonObj.get("height");

        board.setWidth(width);
        board.setHeight(height);
        board.fillWithEmptyCells();

        for (Object obj : jsonObj.getJSONArray("alive_cells")) {
            JSONObject jsonPos = (JSONObject) obj;
            Position pos = new Position((int) jsonPos.get("x"), (int) jsonPos.get("y"));
            if (!board.isValidBoardPosition(pos))
                throw new InvalidPosition(pos + "is not valid.");
            board.setAlive(pos);
        }
    }

}
