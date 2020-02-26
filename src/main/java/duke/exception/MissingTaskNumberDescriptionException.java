package duke.exception;

/**
 * Exception thrown when task number is not specified to "done", "delete" or "find" command.
 */
public class MissingTaskNumberDescriptionException extends DukeException {

    private String errorMsg;

    /**
     * Default constructor for MissingTaskNumberDescriptionException class.
     * @param errorMsg String containing error message
     */
    public MissingTaskNumberDescriptionException(String errorMsg) {
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
