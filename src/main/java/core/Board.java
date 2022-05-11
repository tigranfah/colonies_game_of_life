package core;

import exceptions.InvalidPosition;
import utils.Copyable;
import utils.Matrix;

// the code is written in the using Principle #1.
// That is separation of the data and functionality.
// Data (model) is Board here.
// More about this principle here: https://blog.klipse.tech/databook/2020/10/02/separate-code-data.html
public class Board {

    private Matrix<Cell> cellGrid = null;

    public Board(int width, int height) {
        cellGrid = new Matrix<Cell>(height, width);
        this.fillWithEmptyCells();
    }

    public Board(Matrix<Cell> cellGrid) {
        this.cellGrid = cellGrid;
    }

    protected boolean isValidBoardPosition(Position pos) {
        if (pos == null)
            throw new NullPointerException("Position cannot be null.");
        if (pos.getX() >= 0 && pos.getX() < this.getWidth() &&
                pos.getY() >= 0 && pos.getY() < this.getHeight()) return true;
        return false;
    }

    private void fillWithEmptyCells() {
        if (this.getWidth() == 0 || this.getHeight() == 0)
            throw new RuntimeException("Board instance variables are not initialized properly.");

        this.cellGrid = new Matrix<Cell>(this.getHeight(), this.getWidth());

        for (int i = 0; i < this.getHeight(); ++i) {
            for (int j = 0; j < this.getWidth(); ++j) {
                this.cellGrid.set(new Cell(0), i, j);
            }
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
        return new Cell(this.cellGrid.get(pos.getY(), pos.getX()));
    }

    //*
    // Returns the actual reference to the object for fast access.
    // /
    public Cell getCellAt(Position pos) {
        if (pos == null)
            throw new NullPointerException("Position cannot be null.");
        return this.cellGrid.get(pos.getY(), pos.getX());
    }

    //*
    // Mutator for gridBoard instance variable.
    // Sets the reference of the cell grid.
    // /
    public void setCellGrid(Matrix<Cell> cells) {
        if (cells == null)
            throw new NullPointerException("Grid cell cannot be null.");
        this.cellGrid = cells;
    }

    //*
    // Accessor (privacy leak) for gridBoard instance variable.
    // This method should most likely be used for read only purposes to
    // access the variable faster without copying it.
    // /
    public Matrix<Cell> getCellGrid() {
        return this.cellGrid;
    }

    public int getWidth() {
        return this.cellGrid.getWidth();
    }

    public int getHeight() {
        return this.cellGrid.getHeight();
    }

    public String toString() {
        String strRepr = "Board (" + this.getHeight() + ", " + this.getWidth() + ")";
        for (int i = 0; i < this.getHeight(); ++i) {
            strRepr += "\n";
            for (int j = 0; j < this.getWidth(); ++j) {
                Cell cell = this.getCellAt(new Position(j, i));
                strRepr += cell.toString() + " ";
            }
        }
        return strRepr;
    }

}
