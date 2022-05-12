package utils;

import core.Cell;
import utils.Matrix;

//*
// Pattern is simply special case of Matrix. Matrix of integers.
// e.g.
// 0 1 0 2
// 5 6 1 0
// 6 1 0 0
// In game of life there are well known patters that show certain,
// deterministic behaviour. This is class is a type to store this kind of cases (patterns).
// /
public class Pattern extends Matrix<Integer> {

    public Pattern(int height, int width) { super(height, width); }

    public Pattern(int[][] intMatrix) {
        super(intMatrix.length, intMatrix[0].length);

        for (int i = 0; i < this.getHeight(); ++i) {
            for (int j = 0; j < this.getWidth(); ++j) {
                try {
                    this.set(new Integer(intMatrix[i][j]), i, j);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
