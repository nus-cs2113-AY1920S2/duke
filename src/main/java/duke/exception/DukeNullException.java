package duke.exception;

/**
 * The DukeNullException class is an Exception that is thrown when there is an invalid
 * command provided by the user.
 * DukeNullException class extends from DukeException class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DukeNullException extends DukeException {
    /**
     * Public constructor for DukeNullException.
     * @param message Error message shown to the user.
     */
    public DukeNullException(String message) {
        super(message);
    }
}
