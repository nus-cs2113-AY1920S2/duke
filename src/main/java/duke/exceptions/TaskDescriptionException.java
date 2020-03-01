package duke.exceptions;

/**
 * Throws exception when task number is not specified.
 */
public class TaskDescriptionException extends DukeException {
    private String error;

    /**
     * Constructor of TaskDescriptionException class.
     * @param error String containing error message.
     */
    public TaskDescriptionException(String error) {
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