public class GameOfLifeBoard {
    public static void main(String[] args) {
        GameOfLifeBoard testBoard = new GameOfLifeBoard(5, 8);
        testBoard.setAlive(2, 2);
        testBoard.setAlive(2, 3);
        testBoard.setAlive(2, 4);
        testBoard.printBoard();
        System.out.println(testBoard.numberOfAliveNeighbours(2, 3));
        testBoard.step();
        testBoard.printBoard();
        testBoard.step();
        testBoard.printBoard();
    }
    int width;
    int height;
    int[][] board;

    public GameOfLifeBoard(int height, int width) {
        this.width = width;
        this.height = height;
        this.board = new int[height][width];
    }
    public void printBoard() {
        System.out.println();
        for (int i = 0; i < height; i++) {
            String row = "[";
            for (int j = 0; j < width; j++) {
                if (this.board[i][j] == 0) {
                    row += ".";
                }
                else {
                    row += "*";
                }
            }
            row += "]";
            System.out.println(row);
        }
        System.out.println();
    }
    public void setAlive(int i, int j) {
        this.board[i][j] = 1;
    }
    public void setDead(int i, int j) {
        this.board[i][j] = 0;
    }
    public int numberOfAliveNeighbours(int i, int j) {
        int number = 0;
        number += isValidState(i-1, j-1);
        number += isValidState(i-1, j);
        number += isValidState(i-1, j+1);
        number += isValidState(i, j-1);
        number += isValidState(i, j+1);
        number += isValidState(i+1, j-1);
        number += isValidState(i+1, j);
        number += isValidState(i+1, j+1);
        return number;
    }
    public void step() {
        int[][] newBoard = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int aliveNeighbours = numberOfAliveNeighbours(i, j);
                if (this.board[i][j] == 1) {
                    if (aliveNeighbours < 2) {
                        newBoard[i][j] = 0;
                    }
                    else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        newBoard[i][j] = 1;
                    }
                    else if (aliveNeighbours > 3) {
                        newBoard[i][j] = 0;
                    }
                }
                else {
                    if (aliveNeighbours == 3) {
                        newBoard[i][j] = 1;
                    }
                }
            }
        }
        this.board = newBoard;
    }
    public int isValidState(int i, int j) {
        if (j < 0 || j >= width || i < 0 || i >= height) {
            return 0;
        }
        return this.board[i][j];
    }
}
