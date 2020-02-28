package duke.exceptions;

/**
 * Exception to represent an error that occurred when reading from file due to the file being wrongly formatted.
 */
public class BadFileFormatException extends Exception {
    public BadFileFormatException(String errorMessage){
        super("Bad file format:" + System.lineSeparator() + errorMessage);
    }
}
