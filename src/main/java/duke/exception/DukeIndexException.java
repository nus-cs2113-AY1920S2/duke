package duke.exception;

/**
 * The DukeIndexException class is an Exception that is thrown when the user input wrong index format.
 * DukeIndexException class extends from DukeException class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DukeIndexException extends DukeException {
    /**
     * Public constructor for DukeIndexException.
     * @param message Error message shown to the user.
     */
    public DukeIndexException(String message) {
        super(message);
    }
}
