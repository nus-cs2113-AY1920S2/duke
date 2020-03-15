package duke.exception;

/**
 * The DukeDateTimeException class is an Exception that is thrown when the user input wrong Date or Time format.
 * DukeDateTimeException class extends from DukeException class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DukeDateTimeException extends DukeException {
    /**
     * Public constructor for DukeDateTimeException.
     * @param message Error message shown to the user.
     */
    public DukeDateTimeException(String message) {
        super(message);
    }
}
