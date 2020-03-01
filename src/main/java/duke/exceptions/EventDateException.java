package duke.exceptions;

/**
 * Throws exception when "/at" date is missing.
 */
public class EventDateException extends DukeException {

    private String error;

    /**
     * Constructor of EventDateException class.
     * @param error String containing error message.
     */
    public EventDateException(String error) {
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