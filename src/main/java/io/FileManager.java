package io;

import java.util.Scanner;
import java.io.*;

import core.ColonyCell;
import utils.Pattern;
import exceptions.InvalidFileFormat;
import utils.Matrix;
import core.Board;

public final class FileManager {

    private static final String PATH_TO_PATTERNS_DIR = "src/main/resources/";
    private static final String EXTENSION = ".txt";
    private static final String CURRENT_DIR = System.getProperty("user.dir");

    public static String extractNameFromPath(String filePath) {
        String[] splitPath = filePath.split("/");
        String name = splitPath[splitPath.length - 1];
        int lastIndexOf = name.lastIndexOf(".");

        if (lastIndexOf == -1)
            return "";

        return name.substring(0, lastIndexOf);
    }

    public static String constructPath(String name) {
        return PATH_TO_PATTERNS_DIR + name + EXTENSION;
    }

    public static boolean isValidFile(String path) {
        File file = new File(path);
        return (file.isFile() && file.getName().endsWith(EXTENSION));
    }

    public static class FileReader {

        public static Pattern readPatternFromFile(String name) {
            String filePath = constructPath(name);
            if (!isValidFile(filePath))
                throw new IllegalArgumentException(filePath + " is not a valid or accepted file.");
            FileInputStream inStream = null;
            try {
                inStream = new FileInputStream(filePath);
                Pattern pat = FileReader.extractMatrixInfoFromFile(inStream);
                pat.setName(name);
                return pat;
            } catch (InvalidFileFormat e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.out.printf("%s: File could not be found or opened.", filePath);
            } finally {
                try {
                    if (inStream != null)
                        inStream.close();
                } catch (IOException e) {
                    System.out.printf("%s: IO exception while closing.", filePath);
                }
            }
            return null;
        }

        public static Board readBoardStateFromFile(String name) {
            String filePath = constructPath(name);
            if (!isValidFile(filePath))
                throw new IllegalArgumentException(filePath + " is not a valid or accepted file.");
            FileInputStream inStream = null;
            try {
                inStream = new FileInputStream(filePath);
                Matrix<ColonyCell> cellGrid = Matrix.convertIntToCellMatrix(
                        FileReader.extractMatrixInfoFromFile(inStream)
                );
                return new Board(cellGrid);
            } catch (InvalidFileFormat e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.out.printf("%s: File could not be found or opened.", filePath);
            } finally {
                try {
                    if (inStream != null)
                        inStream.close();
                } catch (IOException e) {
                    System.out.printf("%s: IO exception while closing.", filePath);
                }
            }
            return null;
        }

        private static Pattern extractMatrixInfoFromFile(FileInputStream inStream) throws InvalidFileFormat {
            Scanner sc = new Scanner(inStream);
            int width, height;

            if (sc.hasNextInt())
                height = sc.nextInt();
            else throw new InvalidFileFormat();
            if (sc.hasNextInt())
                width = sc.nextInt();
            else throw new InvalidFileFormat();

            Pattern gridCell = new Pattern(height, width);
            for (int i = 0; i < height; ++i) {
                // \n char
                if (sc.hasNextLine())
                    sc.nextLine();
                else throw new InvalidFileFormat();
                for (int j = 0; j < width; ++j) {
                    if (sc.hasNextInt()) {
                        int a = sc.nextInt();
                        gridCell.set(a, i, j);
                    } else throw new InvalidFileFormat();
                }
            }

            return gridCell;
        }

    }

    public static class FileWriter {

        public static void writeBoardStateToFile(Board board, String filePath) {
            PrintWriter writer = null;
            try {
                File file = new File(filePath);
                writer = new PrintWriter(file);
                FileWriter.extractBoardInfoToFile(board, writer);
            } catch (FileNotFoundException e) {
                System.out.printf("%s: File could not be found or opened.", filePath);
            } finally {
                if (writer != null)
                    writer.close();
            }
        }

        public static void writePatternToFile(Pattern pattern, String filePath) {
            PrintWriter writer = null;
            try {
                File file = new File(filePath);
                writer = new PrintWriter(file);
                FileWriter.extractMatrixInfoToFile(pattern, writer);
            } catch (FileNotFoundException e) {
                System.out.printf("%s: File could not be found or opened.", filePath);
            } finally {
                if (writer != null)
                    writer.close();
            }
        }

        private static void extractBoardInfoToFile(Board board, PrintWriter writer) {
            FileWriter.extractMatrixInfoToFile(
                    Matrix.convertCellToIntegerMatrix(board.getCellGrid()),
                    writer
            );
        }

        private static void extractMatrixInfoToFile(Matrix<Integer> matrix, PrintWriter writer) {
            writer.print(matrix.getHeight() + " " + matrix.getWidth());
            for (int i = 0; i < matrix.getHeight(); ++i) {
                writer.print("\n");
                for (int j = 0; j < matrix.getWidth(); ++j) {
                    writer.print(matrix.get(i, j) + " ");
                }
            }
        }

    }

}
