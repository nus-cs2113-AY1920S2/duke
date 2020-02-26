package duke.exception;

/**
 * Exception thrown when no task number is supplied to "Find", "Delete" and "Done" command.
 */
public class MissingTaskNumberException extends DukeException {

    private String errorMsg;

    /**
     * Default constructor for MissingTaskNumberException class.
     * @param errorMsg String containing error message
     */
    public MissingTaskNumberException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Formats error message.
     * @return String containing the error message to be displayed
     */
    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}
