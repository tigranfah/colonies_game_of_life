package utils;

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
        strRepr += "(" + this.height + ", " + this.width + ")\n";
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j)
                strRepr += this.get(i, j).toString() + "\t";
        }
        return strRepr;
    }

}
