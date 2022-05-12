package core;

import utils.Pair;

public class Utils {

    protected static final char DEFAULT_DEAD_CELL_CHARACTER = '.';
    protected static final char DEFAULT_ALIVE_CELL_CHARACTER = 'o';

    private static int[][] CHARACTER_REPR_OF_INTEGERS = null;

    private static void initCharReprOfInts() {

        String ASCIILowerCase = "abcdefghijklmnopqrstuvwxyz";
        String ASCIIUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Utils.CHARACTER_REPR_OF_INTEGERS = new int[ASCIILowerCase.length() + 1][3];

        Utils.CHARACTER_REPR_OF_INTEGERS[0] =
                new int[] { 0, (int) DEFAULT_DEAD_CELL_CHARACTER, (int) DEFAULT_ALIVE_CELL_CHARACTER };

        for (int i = 1; i < ASCIILowerCase.length() + 1; ++i) {
            Utils.CHARACTER_REPR_OF_INTEGERS[i] =
                    new int[] { i, (int) ASCIILowerCase.charAt(i - 1), (int) ASCIIUpperCase.charAt(i - 1) };
        }
    }

    protected static Pair<Character, Character> getCharPairByIndex(int index) {
        if (Utils.CHARACTER_REPR_OF_INTEGERS == null);
            Utils.initCharReprOfInts();

        int[] iRow = Utils.CHARACTER_REPR_OF_INTEGERS[index];
        return new Pair<Character, Character>((char) iRow[1], (char) iRow[2]);
    }

}