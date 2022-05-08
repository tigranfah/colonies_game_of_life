package utils;

import core.Cell;

public class Matrix<ElemType> implements Cloneable {

    private int width, height;
    private ElemType[][] matrixElements;

    public Matrix(int height, int width) {
        this.height = height;
        this.width = width;
        this.matrixElements = (ElemType[][]) new Object[height][width];
    }

    private ElemType[][] getMatrixElementsCopy() {
        ElemType[][] newMatrixElems = (ElemType[][]) new Object[this.height][this.width];
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j)
                // INCORRECT, shallow copy
                newMatrixElems[i][j] = this.get(i, j);
        }
        return newMatrixElems;
    }

    public Matrix<ElemType> clone() {
        try {
            Matrix<ElemType> cloned = (Matrix<ElemType>) super.clone();
            cloned.matrixElements = this.getMatrixElementsCopy();
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }

    public ElemType get(int y, int x) {
        return this.matrixElements[y][x];
    }

    public void set(ElemType val, int y, int x) {
        this.matrixElements[y][x] = val;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public String toString() {
        String strRepr = "";
        strRepr += "(" + this.height + ", " + this.width + ")";
        for (int i = 0; i < height; ++i) {
            strRepr += "\n";
            for (int j = 0; j < width; ++j)
                strRepr += this.get(i, j).toString() + "\t";
        }
        return strRepr;
    }

    public static Matrix<Integer> convertCellToIntegerMatrix(Matrix<Cell> cellMatrix) {
        Matrix<Integer> intMatrix = new Matrix<Integer>(cellMatrix.getHeight(), cellMatrix.getWidth());
        for (int i = 0; i < cellMatrix.getHeight(); ++i) {
            for (int j = 0; j < cellMatrix.getWidth(); ++j)
                intMatrix.set(cellMatrix.get(i, j).getColonyIndex(), i, j);
        }
        return intMatrix;
    }

    public static Matrix<Cell> convertIntToCellMatrix(Matrix<Integer> intMatrix) {
        Matrix<Cell> cellMatrix = new Matrix<>(intMatrix.getHeight(), intMatrix.getWidth());
        for (int i = 0; i < intMatrix.getHeight(); ++i) {
            for (int j = 0; j < intMatrix.getWidth(); ++j)
                cellMatrix.set(new Cell(intMatrix.get(i, j)), i, j);
        }
        return cellMatrix;
    }

}
