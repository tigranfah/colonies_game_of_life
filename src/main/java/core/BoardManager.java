package core;

public class BoardManager {

    private Board board;

    public BoardManager(int width, int height) {
        this.board = new Board(width, height);
    }

    public void step() {

        int setAliveCount = 0;
        int width =  this.board.getWidth();
        int height = this.board.getHeight();
        Position[] toSetAlivePositions = new Position[width * height];

        int setDeadCount = 0;
        Position[] toSetDeadPositions = new Position[width * height];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                Position currentPosition = new Position(j, i);
//                System.out.println(currentPosition);
                int numberOfNeighbours = this.getNumberOfAliveNeighbours(currentPosition);
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

    public void setAlive(Position pos) {
        this.board.getCellAt(pos).setAlive(1);
    }

    public void setDead(Position pos) {
        this.board.getCellAt(pos).setDead();
    }

    public boolean isCellAlive(Position pos) {
        return this.board.getCellAt(pos).isAlive();
    }

    private int getNumberOfAliveNeighbours(Position pos) {
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
            if (this.board.isValidBoardPosition(currentPos)) {
                if (this.isCellAlive(currentPos)) {
                    ++numberOfAliveNeighbours;
                }
            }
        }

        return numberOfAliveNeighbours;
    }

    public Board getBoard() {
        return this.board;
    }

    public Board getBoardCopy() {
        return this.board.clone();
    }

}
