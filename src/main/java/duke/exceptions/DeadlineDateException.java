package duke.exceptions;

/**
 * Throws exception when "/by" date is missing.
 */
public class DeadlineDateException extends DukeException {
    private String error;

    /**
     * Constructor of DeadlineDateException class.
     * @param error String containing error message.
     */
    public DeadlineDateException(String error) {
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