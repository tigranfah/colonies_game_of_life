package utils;

import core.Position;

public class Utils {

    public static Position[] resizePartiallyFilledPositionArray(Position[] positions, int size) {
        Position[] resizedPositions = new Position[size];

        for (int i = 0; i < size; ++i)
            resizedPositions[i] = positions[i];

        return resizedPositions;
    }

}
