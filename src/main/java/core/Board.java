package core;

// no need to add the extra GameOfLife* in front of the classes, its evident
public class Board {

    private static final String ALIVE_CELL = " o ";
    private static final String DEAD_CELL = " . ";

    private int width;
    private int height;
    private Cell[][] board;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Cell[height][width];
        this.fillWithEmptyCells();
    }

    private void fillWithEmptyCells() {
        if (this.board == null || this.width == 0 || this.height == 0) {
            Errors.RuntimeWarning("the Board instance variables are not initialized properly.");
            return;
        }

        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                this.board[i][j] = new Cell(false);
            }
        }
    }

    public int getNumberAliveNeighbours(Position pos) {
        int[][] posInds = {
                // up left
                {pos.getX() - 1, pos.getY() + 1},
                // up
                {pos.getX(), pos.getY() + 1},
                // up right
                {pos.getX() + 1, pos.getY() + 1},
                // right
                {pos.getX() + 1, pos.getY()},
                // right down
                {pos.getX() + 1, pos.getY() - 1},
                // down
                {pos.getX(), pos.getY() - 1},
                // left down
                {pos.getX() - 1, pos.getY() - 1},
                // left
                {pos.getX() - 1, pos.getY()},
        };

        int numberOfAliveNeighbours = 0;

        for (int[] inds : posInds) {
            Position currentPos = new Position(inds[0], inds[1]);
            if (this.isValidBoardPosition(currentPos)) {
                if (this.isCellAlive(currentPos)) {
                    ++numberOfAliveNeighbours;
                }
            }
        }

//        System.out.printf("%d ", numberOfAliveNeighbours);
        return numberOfAliveNeighbours;
    }

    public void step() {

        int setAliveCount = 0;
        Position[] toSetAlivePositions = new Position[this.width * this.height];

        int setDeadCount = 0;
        Position[] toSetDeadPositions = new Position[this.width * this.height];

        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                Position currentPosition = new Position(j, i);
//                System.out.println(currentPosition);
                int numberOfNeighbours = this.getNumberAliveNeighbours(currentPosition);
                if (this.isCellAlive(currentPosition)) {
                    if (numberOfNeighbours < 2 || numberOfNeighbours > 3)
                        toSetDeadPositions[setDeadCount++] = currentPosition;
                }
                else if (!this.isCellAlive(currentPosition)) {
                    if (numberOfNeighbours == 3)
                        toSetAlivePositions[setAliveCount++] = currentPosition;
                }
            }
        }

        for (int i = 0; i < setAliveCount; ++i)
            this.setAlive(toSetAlivePositions[i]);

        for (int i = 0; i < setDeadCount; ++i)
            this.setDead(toSetDeadPositions[i]);
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
        this.board[pos.getY()][pos.getX()].setAlive();
    }

    public void setDead(Position pos) {
        this.board[pos.getY()][pos.getX()].setDead();
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
        if (!this.isValidBoardPosition(pos)) {
            Errors.RuntimeWarning(pos.getX() + " and " + pos.getX() + " are not valid value for position.");
        }
        Cell cell = this.getCellAt(pos);
        return cell.isAlive();
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

    public String toString() {
        String strRepr = "";
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                Cell cell = this.getCellAt(new Position(j, i));
                strRepr += cell.toString();
            }
        }
        return strRepr;
    }

}
