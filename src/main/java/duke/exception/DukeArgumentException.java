package duke.exception;

/**
 * The DukeArgumentException class is an Exception that is thrown when there are either
 * missing parameter or too many parameter provided by the user.
 * DukeArgumentException class extends from IllegalArgumentException class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DukeArgumentException extends IllegalArgumentException {
    /**
     * Public constructor for DukeArgumentException.
     * @param message Error message shown to the user.
     */
    public DukeArgumentException(String message) {
        super(message);
    }
}
