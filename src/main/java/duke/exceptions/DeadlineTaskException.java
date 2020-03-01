package duke.exceptions;

/**
 * Throws exception when task description is missing.
 */
public class DeadlineTaskException extends DukeException {
    private String error;

    /**
     * Constructor of DeadlineTaskException class.
     * @param error String containing error message.
     */
    public DeadlineTaskException(String error) {
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