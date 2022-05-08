package io;

import java.util.Scanner;
import java.io.*;

import core.Cell;
import core.Position;
import exceptions.InvalidFileFormat;
import utils.Matrix;
import core.Board;

public final class FileManager {

    private String extension = ".txt";

    private static class FileReader {

        public static Matrix<Integer> readPatternFromFile(String filePath) {
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
                Matrix<Integer> intMatrix = FileReader.extractMatrixInfoFromFile(inStream);
                Matrix<Cell> cellGrid = new Matrix<>(intMatrix.getHeight(), intMatrix.getWidth());
                for (int i = 0; i < cellGrid.getHeight(); ++i) {
                    for (int j = 0; j < cellGrid.getWidth(); ++j)
                        cellGrid.set(new Cell(intMatrix.get(i, j)), i, j);
                }
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

        public static Matrix<Integer> extractMatrixInfoFromFile(FileInputStream inStream) throws InvalidFileFormat {
            Scanner sc = new Scanner(inStream);
            int width, height;

            if (sc.hasNextInt())
                width = sc.nextInt();
            else throw new InvalidFileFormat();
            if (sc.hasNextInt())
                height = sc.nextInt();
            else throw new InvalidFileFormat();

            Matrix<Integer> gridCell = new Matrix<Integer>(height, width);
            for (int i = 0; i < height; ++i) {
                // \n char
                sc.nextLine();
                for (int j = 0; j < width; ++j) {
                    if (sc.hasNextInt())
                    gridCell.set(sc.nextInt(), i, j);
                }
            }

            return gridCell;
        }

    }

    public static class FileWriter {

        public static void writeBoardStateToFile(Board board, String filePath) {
            PrintWriter writer = null;
            FileOutputStream outStream = null;
            try {
                outStream = new FileOutputStream(filePath);
                writer = new PrintWriter(outStream);
                FileWriter.extractBoardInfoToFile(board, writer);
            } catch (FileNotFoundException e) {
                System.out.printf("%s: File could not be found or opened.", filePath);
            } finally {
                try {
                    outStream.close();
                } catch (IOException e) {
                    System.out.printf("%s: IO exception while closing.", filePath);
                }
            }
        }

        public static void extractBoardInfoToFile(Board board, PrintWriter writer) {
            writer.println(board.getWidth() + " " + board.getHeight());
            for (int i = 0; i < board.getHeight(); ++i) {
                writer.print("\n");
                for (int j = 0; j < board.getWidth(); ++j) {
                    writer.print(board.getCellAt(new Position(j, i)) + " ");
                }
            }
        }

    }

}
