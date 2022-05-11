package engine.exceptions;

public class WindowCreationException extends Exception{
    public WindowCreationException(String message) {
        super("Failed to create the window. \n" + message);
    }
}
