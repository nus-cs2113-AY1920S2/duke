package duke.exception;

/**
 * The custom exception thrown when for the <code>DONE</code> and <code>DELETE</code> task is missing the task number
 */
public class MissingNumberFieldException extends Exception {
    public MissingNumberFieldException(String s) {
        super(s);
    }
}
