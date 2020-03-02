package duke.exception;

/**
 * The DukeIndexException class is an Exception that is thrown when the index provided by
 * the user is out of bound. In such cases, the index provided is either negative or greater
 * than total number of Task stored.
 * DukeIndexException class extends from IndexOutOfBoundsException class.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DukeIndexException extends IndexOutOfBoundsException {
    /**
     * Public constructor for DukeIndexException.
     * @param message Error message shown to the user.
     */
    public DukeIndexException(String message) {
        super(message);
    }
}
