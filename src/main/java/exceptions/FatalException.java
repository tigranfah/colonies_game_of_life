package exceptions;

public class FatalException {

    public FatalException() {
        System.out.println("Fatal exception. Exiting...");
        System.exit(1);
    }

    public FatalException(String message) {
        System.out.println("Fatal exception. " + message + ". Exiting...");
        System.exit(1);
    }

}
