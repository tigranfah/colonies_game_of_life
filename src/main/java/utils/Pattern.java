package utils;

import core.Colony;
import core.ColonyCell;
import core.Worker;

import java.util.ArrayList;

public class Pattern extends Matrix<Integer> {

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

    public void setName(String name) {
        this.name = name;
    }

    public Matrix<ColonyCell> toColonyMatrix(int indexOfColony) {
        Matrix<ColonyCell> cellMatrix = new Matrix<ColonyCell>(this.getHeight(), this.getWidth());
        for (int i = 0; i < this.getHeight(); ++i) {
            for (int j = 0; j < this.getWidth(); ++j) {
                int value = this.get(i, j);
                if (value != 0)
                    cellMatrix.set(new ColonyCell(indexOfColony), i, j);
            }
        }
        return cellMatrix;
    }

}
