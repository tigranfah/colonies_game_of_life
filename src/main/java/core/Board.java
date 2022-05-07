package core;

import exceptions.InvalidPosition;

public class Board implements Cloneable {

    private int width = 0;
    private int height = 0;
    private Cell[][] cellGrid = null;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.fillWithEmptyCells();
    }

    public Board(Board other) {
        this.width = other.width;
        this.height = other.height;
        this.setCellGrid(other.getCellGridCopy());
    }

    public void fillWithEmptyCells() {
        if (this.width == 0 || this.height == 0)
            throw new RuntimeException("Board instance variables are not initialized properly.");

        this.cellGrid = new Cell[this.height][this.width];

        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                this.cellGrid[i][j] = new Cell(false);
            }
        }
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
            if (this.isValidBoardPosition(currentPos)) {
                if (this.isCellAlive(currentPos)) {
                    ++numberOfAliveNeighbours;
                }
            }
        }

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

    public Board clone() {
        try {
            Board cloned = (Board) super.clone();
            cloned.setCellGrid(this.getCellGridCopy());
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void setAlive(Position pos) {
        if (pos == null)
            throw new NullPointerException();
        this.cellGrid[pos.getY()][pos.getX()].setAlive();
    }

    public void setDead(Position pos) {
        if (pos == null)
            throw new NullPointerException();
        this.cellGrid[pos.getY()][pos.getX()].setDead();
    }

    //*
    // Returns copy of the object.
    // /
    public Cell getCellCopyAt(Position pos) {
        if (pos == null)
            throw new NullPointerException();
        if (!this.isValidBoardPosition(pos))
            throw new InvalidPosition(pos);
        return new Cell(this.cellGrid[pos.getY()][pos.getX()]);
    }

    //*
    // Returns the actual reference to the object for fast access.
    // /
    public Cell getCellAt(Position pos) {
        return this.cellGrid[pos.getY()][pos.getX()];
    }

    public boolean isCellAlive(Position pos) {
        Cell cell = this.getCellAt(pos);
        return cell.isAlive();
    }

    public boolean isValidBoardPosition(Position pos) {
        if (pos == null)
            throw new NullPointerException();
        if (pos.getX() >= 0 && pos.getX() < this.width &&
            pos.getY() >= 0 && pos.getY() < this.height) return true;
        return false;
    }

    //*
    // Mutator for gridBoard instance variable.
    // Sets the reference of the cell grid.
    // /
    public void setCellGrid(Cell[][] cells) {
        this.cellGrid = cells;
    }

    //*
    // Accessor (privacy leak) for gridBoard instance variable.
    // This method should most likely be used for read only purposes to
    // access the variable faster without copying it.
    // /
    public Cell[][] getCellGrid() {
        return this.cellGrid;
    }

    //*
    // Accessor for gridBoard instance variable.
    // Returns a deep copy of the board instance variable.
    // /
    public Cell[][] getCellGridCopy() {
        Cell[][] newCopy = new Cell[this.height][this.width];
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j)
                newCopy[i][j] = this.cellGrid[i][j].clone();
        }
        return newCopy;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
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
