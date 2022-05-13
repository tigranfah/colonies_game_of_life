package exceptions;

public class InvalidCommandLineArgumentException extends Exception {

    public InvalidCommandLineArgumentException() {
        System.out.println("Invalid command line argument.");
        System.exit(0);
    }

}
