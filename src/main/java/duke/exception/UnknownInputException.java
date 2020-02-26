package duke.exception;

/**
 * Exception thrown when an unknown input is given to Duke.
 */
public class UnknownInputException extends DukeException {

    private String errorMsg;

    /**
     * Default constructor for UnknownInputException.
     * @param errorMsg String containing error message
     */
    public UnknownInputException(String errorMsg) {
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
