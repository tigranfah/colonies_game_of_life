package exceptions;

import core.Position;

public class InvalidPosition extends RuntimeException {

    public InvalidPosition(String message) {
        super(message);
    }

    public InvalidPosition(Position pos) {
        super();
        System.out.println(pos + " is not a valid position on the board.");
    }

}