package Duke.Exception;

/**
 * The custom exception thrown when the task is missing information in the description field
 */
public class MissingDescriptonException extends Exception {
    public MissingDescriptonException(String s) {
        super(s);
    }

}
