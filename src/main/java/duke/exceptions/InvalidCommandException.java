package duke.exceptions;

/**
 * Throws exception when invalid command is provided as user input.
 */
public class InvalidCommandException extends DukeException {
    private String error;

    /**
     * Constructor of InvalidCommandException class.
     * @param error String containing error message.
     */
    public InvalidCommandException(String error) {
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
