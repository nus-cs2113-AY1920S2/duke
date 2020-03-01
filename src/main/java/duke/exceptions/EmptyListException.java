package duke.exceptions;

/**
 * Throws Exception when task list is empty when user inputs "list" as command.
 */
public class EmptyListException extends DukeException {

    private String error;

    /**
     * Constructor of EmptyListException class.
     * @param error String containing error message.
     */
    public EmptyListException(String error) {
        this.error = error;
    }

    /**
     * Formatting of error message.
     * @return error String to display error message.
     */
    @Override
    public String toString() {
        return String.format("Error: %s", this.error);
    }
}