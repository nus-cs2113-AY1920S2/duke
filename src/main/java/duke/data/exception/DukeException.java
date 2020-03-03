package duke.data.exception;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class DukeException extends Exception {
    /**
     * @param message Contain relevant information on the failed constraint(s)
     */
    public DukeException(String message) {
        super(message);
    }
}
