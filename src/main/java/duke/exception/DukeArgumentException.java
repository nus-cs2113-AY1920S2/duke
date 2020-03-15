package duke.exception;

/**
 * The DukeArgumentException class is an Exception that is thrown when there are either
 * missing argument or too many arguments provided by the user.
 * DukeArgumentException class extends from DukeException class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DukeArgumentException extends DukeException {
    /**
     * Public constructor for DukeArgumentException.
     * @param message Error message shown to the user.
     */
    public DukeArgumentException(String message) {
        super(message);
    }
}
