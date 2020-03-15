package duke.exception;

/**
 * The DukeException class is all the Exception that might be thrown by Duke program.
 * DukeException class extends from Exception class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DukeException extends Exception {
    /**
     * Public constructor for DukeException.
     * @param message Error message shown to the user.
     */
    public DukeException(String message) {
        super(message);
    }
}
