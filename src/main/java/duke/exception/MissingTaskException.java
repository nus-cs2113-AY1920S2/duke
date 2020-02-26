package duke.exception;

/**
 * Exception thrown when task description is not supplied.
 */
public class MissingTaskException extends DukeException {

    private String errorMsg;

    /**
     * Default constructor for MissingTaskException class.
     * @param errorMsg String containing error message
     */
    public MissingTaskException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Formats error message
     * @return String containing error message to be displayed.
     */
    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }


}
