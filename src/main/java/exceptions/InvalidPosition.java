package exceptions;

import core.Position;

public class InvalidPosition extends RuntimeException {

    public InvalidPosition(Position pos) {
        super();
        System.out.println(pos + " is not a valid position on the board.");
        System.exit(0);
    }

}