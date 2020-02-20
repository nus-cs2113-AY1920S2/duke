package Duke.Exception;

/**
 * The custom exception thrown when the task is missing information in the description field
 */
public class MissingDescriptionException extends Exception {
    public MissingDescriptionException(String s) {
        super(s);
    }

}
