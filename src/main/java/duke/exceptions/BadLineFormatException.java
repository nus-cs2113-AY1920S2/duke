package duke.exceptions;

/**
 * Exception to represent a badly formatted line.
 * This can be in reference to a line being read from file or a user input line.
 */
public class BadLineFormatException extends Exception {
    public BadLineFormatException(String errorMessage){
        super(errorMessage);
    }
}
