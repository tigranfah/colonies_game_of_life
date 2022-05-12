package core;

import exceptions.InvalidPosition;
import utils.Matrix;

// the code is written in the using Principle #1.
// That is separation of the data and functionality.
// Data (model) is Board here.
// More about this principle here: https://blog.klipse.tech/databook/2020/10/02/separate-code-data.html
public class Board {

    private Matrix<ColonyCell> cellGrid = null;

    public Board(int width, int height) {
        cellGrid = new Matrix<ColonyCell>(height, width);
        this.fillWithEmptyCells();
    }

    public Board(Matrix<ColonyCell> cellGrid) {
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

        this.cellGrid = new Matrix<ColonyCell>(this.getHeight(), this.getWidth());

        for (int i = 0; i < this.getHeight(); ++i) {
            for (int j = 0; j < this.getWidth(); ++j) {
                this.cellGrid.set(new ColonyCell(0), i, j);
            }
        }
    }

    public <Type extends ColonyCell> void setBoardSubpart(Matrix<Type> cellMatrix, Position atPos, boolean centered) {
        if (centered) {
            atPos.setX(atPos.getX() - (cellMatrix.getWidth() / 2));
            atPos.setY(atPos.getY() - (cellMatrix.getHeight() / 2));
        }
        for (int i = 0; i < cellMatrix.getHeight(); ++i) {
            for (int j = 0; j < cellMatrix.getWidth(); ++j) {
                Position curPos = new Position(j + atPos.getX(), i + atPos.getY());
                if (!isValidBoardPosition(curPos)) continue;
                if (isKingAt(curPos)) continue;
                Type cell = cellMatrix.get(i, j);
                if (cell.isAlive())
                    this.setCellAt(cell, curPos);
            }
        }
    }

    public boolean isKingAt(Position pos) {
        return this.getCellAt(pos) instanceof King;
    }

    public boolean isWorkerAt(Position pos) {
        return this.getCellAt(pos) instanceof Worker;
    }

    //*
    // Returns copy of the object.
    // /
    public ColonyCell getCellCopyAt(Position pos) {
        if (pos == null)
            throw new NullPointerException("Position cannot be null.");
        if (!this.isValidBoardPosition(pos))
            throw new InvalidPosition(pos);
        return this.cellGrid.get(pos.getY(), pos.getX()).clone();
    }

    //*
    // Returns the actual reference to the object for fast access.
    // /
    public ColonyCell getCellAt(Position pos) {
        if (pos == null)
            throw new NullPointerException("Position cannot be null.");
        return this.cellGrid.get(pos.getY(), pos.getX());
    }

    //*
    // Mutator for gridBoard instance variable.
    // Sets the reference of the cell grid.
    // /
    public void setCellGrid(Matrix<ColonyCell> cells) {
        if (cells == null)
            throw new NullPointerException("Grid cell cannot be null.");
        this.cellGrid = cells;
    }

    //*
    // Accessor (privacy leak) for gridBoard instance variable.
    // This method should most likely be used for read only purposes to
    // access the variable faster without copying it.
    // /
    public Matrix<ColonyCell> getCellGrid() {
        return this.cellGrid;
    }

    public void setCellAt(ColonyCell cell, Position pos) {
        this.cellGrid.set(cell, pos.getY(), pos.getX());
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
