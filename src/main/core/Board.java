package exceptions;

// no need to add the extra GameOfLife* in front of the classes, its evident
public class Board {

    private static final String ALIVE_CELL = " o ";
    private static final String DEAD_CELL = " . ";

    private int width;
    private int height;
    private Cell[][] board;

    public Board(int height, int width) {
        this.width = width;
        this.height = height;
        this.board = new Cell[height][width];
    }

//     public void printBoard() {
//         System.out.println();
//         for (int i = 0; i < height; i++) {
// //            String row = "[";
//             String row = "";
//             for (int j = 0; j < width; j++) {
//                 if (this.board[i][j] == 0) {
//                     row += DEAD_CELL;
//                 }
//                 else {
//                     row += ALIVE_CELL;
//                 }
//             }
// //            row += "]";
//             System.out.println(row);
//         }
//         System.out.println();
//         ClearConsole();
//     }

    public void setAlive(Position pos) {
        if (pos == null || !this.isValidBoardPosition(pos))
            throw InvalidPosition(pos + " is an invalid position.");
        this.board[pos.getY()][pos.getX()] = new Cell();
    }

    public void setDead(Position pos) {
        this.board[pos.getY()][pos.getX()] = null;
    }

    // public int numberOfAliveNeighbours(int i, int j) {
    //     int number = 0;
    //     number += isValidState(i-1, j-1);
    //     number += isValidState(i-1, j);
    //     number += isValidState(i-1, j+1);
    //     number += isValidState(i, j-1);
    //     number += isValidState(i, j+1);
    //     number += isValidState(i+1, j-1);
    //     number += isValidState(i+1, j);
    //     number += isValidState(i+1, j+1);
    //     return number;
    // }

    // public void step() {
    //     int[][] newBoard = new int[height][width];
    //     for (int i = 0; i < height; i++) {
    //         for (int j = 0; j < width; j++) {
    //             int aliveNeighbours = numberOfAliveNeighbours(i, j);
    //             if (this.board[i][j] == 1) {
    //                 if (aliveNeighbours < 2) {
    //                     newBoard[i][j] = 0;
    //                 }
    //                 else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
    //                     newBoard[i][j] = 1;
    //                 }
    //                 else if (aliveNeighbours > 3) {
    //                     newBoard[i][j] = 0;
    //                 }
    //             }
    //             else {
    //                 if (aliveNeighbours == 3) {
    //                     newBoard[i][j] = 1;
    //                 }
    //             }
    //         }
    //     }
    //     this.board = newBoard;
    // }

    public boolean isCellAlive(Position pos) {
        if (!Board.isValidBoardPosition(pos)) {
            Errors.RuntimeWarning(i + " and " + j + " are not valid value for position.");
            if (this.board.getCellAt(i, j) != null)
                return true;
        }
        return false;
    }

    // clear the output of the screen to get smooth running console
    public static void ClearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public boolean isValidBoardPosition(Position pos) {
        if (pos.getX() >= 0 && pos.getX() < this.width &&
            pos.getY() >= 0 && pos.getY() < this.height) return true;
        return false;
    }

    public Cell getCellAt(Position pos) {
        return this.board[pos.getY()][pos.getX()];
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
