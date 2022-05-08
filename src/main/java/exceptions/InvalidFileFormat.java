package exceptions;

public class InvalidFileFormat extends Exception {

    public InvalidFileFormat() {
        super();
    }

    public InvalidFileFormat(String filePath) {
        super();
        System.out.println(filePath + " does not have a valid format to extract board information.");
    }
}
