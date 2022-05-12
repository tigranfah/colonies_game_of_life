package utils;

import core.Worker;

public class Pattern extends Matrix<Integer> implements Cloneable {

    private String name;

    public Pattern(int height, int width) {
        super(height, width);
    }

    public Pattern(String name, int height, int width) {
        this(height, width);
        this.name = name;
    }

    public Pattern(int[][] intMatrix) {
        super(intMatrix.length, intMatrix[0].length);

        for (int i = 0; i < this.getHeight(); ++i) {
            for (int j = 0; j < this.getWidth(); ++j) {
                try {
                    this.set(intMatrix[i][j], i, j);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Integer[][] getMatrixElementsCopy() {
        Integer[][] intMatrix = new Integer[this.getHeight()][this.getWidth()];
        for (int i = 0; i < this.getHeight(); ++i) {
            for (int j = 0; j < this.getWidth(); ++j) {
                intMatrix[i][j] = this.get(i, j);
            }
        }
        return intMatrix;
    }

    @Override
    public Pattern clone() {
        try {
            Pattern cloned = (Pattern) super.clone();
            cloned.setMatrixElements(this.getMatrixElementsCopy());
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public Matrix<Worker> toWorkerMatrix(int indexOfColony) {
        Matrix<Worker> cellMatrix = new Matrix<Worker>(this.getHeight(), this.getWidth());
        for (int i = 0; i < this.getHeight(); ++i) {
            for (int j = 0; j < this.getWidth(); ++j) {
                int value = this.get(i, j);
                if (value != 0)
                    cellMatrix.set(new Worker(indexOfColony), i, j);
                else
                    cellMatrix.set(new Worker(0), i, j);
            }
        }
        return cellMatrix;
    }

}
