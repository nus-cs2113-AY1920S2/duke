package duke.exceptions;

/**
 * Throws exception when index of task number is missing.
 */
public class TaskNumberException extends DukeException {
    private String error;

    /**
     * Constructor of TaskNumberException class.
     * @param error String containing error message.
     */
    public TaskNumberException(String error) {
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