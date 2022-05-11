package io;

import java.util.Scanner;
import java.io.*;

import core.Cell;
import utils.Int;
import utils.Pattern;
import exceptions.InvalidFileFormat;
import utils.Matrix;
import core.Board;

public final class FileManager {

    private String extension = ".txt";

    public static class FileReader {

        public static Pattern readPatternFromFile(String filePath) {
            FileInputStream inStream = null;
            try {
                inStream = new FileInputStream(filePath);
                return FileReader.extractMatrixInfoFromFile(inStream);
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

        public static Board readBoardStateFromFile(String filePath) {
            FileInputStream inStream = null;
            try {
                inStream = new FileInputStream(filePath);
                Matrix<Cell> cellGrid = Matrix.convertIntToCellMatrix(
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
                sc.nextLine();
                for (int j = 0; j < width; ++j) {
                    if (sc.hasNextInt())
                    gridCell.set(new Int(sc.nextInt()), i, j);
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

        private static void extractMatrixInfoToFile(Matrix<Int> matrix, PrintWriter writer) {
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
