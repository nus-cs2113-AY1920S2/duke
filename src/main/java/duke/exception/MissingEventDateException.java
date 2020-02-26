package duke.exception;

/**
 * Exception thrown when "/at" date and time is not specified in Event task.
 */
public class MissingEventDateException extends DukeException {

    private String errorMsg;

    /**
     * Default constructor for MissingEventDateException class.
     * @param errorMsg String containing error message
     */
    public MissingEventDateException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Formats the error message.
     * @return String containing the error message to be displayed
     */
    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}
