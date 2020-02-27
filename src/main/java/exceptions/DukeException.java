package exceptions;

/**
 * <h1>DukeException</h1>
 * Handles all exceptions in the Duke program and gives an error statement
 */
public class DukeException extends Exception{
    public DukeException(String message) {
        super("A Duke error has occurred! \n\t" +message);
    }
}
