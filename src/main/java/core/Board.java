package core;

import exceptions.InvalidPosition;

// the code is written in the using Principle #1.
// That is separation of the data and functionality.
// Data (model) is Board here.
// More about this principle here: https://blog.klipse.tech/databook/2020/10/02/separate-code-data.html
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

    protected boolean isValidBoardPosition(Position pos) {
        if (pos == null)
            throw new NullPointerException("Position cannot be null.");
        if (pos.getX() >= 0 && pos.getX() < this.width &&
                pos.getY() >= 0 && pos.getY() < this.height) return true;
        return false;
    }

    private void fillWithEmptyCells() {
        if (this.width == 0 || this.height == 0)
            throw new RuntimeException("Board instance variables are not initialized properly.");

        this.cellGrid = new Cell[this.height][this.width];

        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                this.cellGrid[i][j] = new Cell(0);
            }
        }
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

    //*
    // Returns copy of the object.
    // /
    public Cell getCellCopyAt(Position pos) {
        if (pos == null)
            throw new NullPointerException("Position cannot be null.");
        if (!this.isValidBoardPosition(pos))
            throw new InvalidPosition(pos);
        return new Cell(this.cellGrid[pos.getY()][pos.getX()]);
    }

    //*
    // Returns the actual reference to the object for fast access.
    // /
    public Cell getCellAt(Position pos) {
        if (pos == null)
            throw new NullPointerException("Position cannot be null.");
        return this.cellGrid[pos.getY()][pos.getX()];
    }

    //*
    // Mutator for gridBoard instance variable.
    // Sets the reference of the cell grid.
    // /
    public void setCellGrid(Cell[][] cells) {
        if (cells == null)
            throw new NullPointerException("Grid cell cannot be null.");
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
        if (width < 0)
            throw new IllegalArgumentException("width cannot be " + width);
        this.width = width;
    }

    public void setHeight(int height) {
        if (height < 0)
            throw new IllegalArgumentException("height cannot be " + height);
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
