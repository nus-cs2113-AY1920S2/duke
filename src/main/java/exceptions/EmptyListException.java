package exceptions;

/**
 * Custom Exceptions for Empty list
 */
public class EmptyListException extends DukeException {
    private String errorMessage;

    /**
     * Constructs EmptyListException object
     * @param errorMessage String provided by user
     */
    public EmptyListException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a formatted string
     * @return String formatting the error message
     */
    @Override
    public String toString() {
        return String.format("Empty List: %s Please add an item!", errorMessage);
    }
}