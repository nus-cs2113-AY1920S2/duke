package duke.exception;

/**
 * The DukeFileException class is an Exception that is thrown when there are issue loading or storing database
 * from or into the external hard disk.
 * DukeFileException class extends from DukeException class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DukeFileException extends DukeException {
    /**
     * Public constructor for DukeFileException.
     * @param message Error message shown to the user.
     */
    public DukeFileException(String message) {
        super(message);
    }
}
