package engine.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class for interacting with files
 */
public class FileLoader {

    /**
     *
     * @param path path of file, relative to the project folder
     * @return full content of the file read line by line
     * @throws IOException
     */
    public static String extractContentAsString(String path) throws IOException {
        Scanner sc = new Scanner(new FileInputStream(path));

        // String builder is more efficient than concatenating Strings, as it does not create new instances
        StringBuilder content = new StringBuilder("");

        while(sc.hasNextLine()){
            content.append(sc.nextLine());
            content.append("\n");
        }

        sc.close();

        return content.toString();
    }
}
