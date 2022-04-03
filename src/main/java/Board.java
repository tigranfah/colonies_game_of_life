// no need to add the extra GameOfLife* in front of the classes, its evident
public class Board {

    private static final String ALIVE_CELL = " o ";
    private static final String DEAD_CELL = " . ";

    int width;
    int height;
    int[][] board;

    public Board(int height, int width) {
        this.width = width;
        this.height = height;
        this.board = new int[height][width];
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < height; i++) {
//            String row = "[";
            String row = "";
            for (int j = 0; j < width; j++) {
                if (this.board[i][j] == 0) {
                    row += DEAD_CELL;
                }
                else {
                    row += ALIVE_CELL;
                }
            }
//            row += "]";
            System.out.println(row);
        }
        System.out.println();
        ClearConsole();
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
}
