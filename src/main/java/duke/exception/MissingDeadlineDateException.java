package duke.exception;

/**
 * Exception thrown when "/by" date and time is not specified.
 */
public class MissingDeadlineDateException extends DukeException {

    private String errorMsg;

    /**
     * Default constructor for MissingDeadlineDateException class.
     * @param errorMsg String containing error message
     */
    public MissingDeadlineDateException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Formats error message
     * @return String containing error message to be displayed
     */
    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}
