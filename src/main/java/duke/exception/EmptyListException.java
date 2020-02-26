package duke.exception;

/**
 * Exception thrown when "list" command is supplied but task list is empty.
 */
public class EmptyListException extends DukeException {

    private String errorMsg;

    /**
     * Default constructor of EmptyListException class.
     * @param errorMsg String containing error message
     */
    public EmptyListException(String errorMsg) {
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
