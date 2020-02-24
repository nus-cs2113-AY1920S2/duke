package exceptions;

/**
 * Custom Exception for when user fails to provide an integer index
 */
public class MissingItemIndexException extends DukeException {
    private String errorMessage;

    /**
     * Constructs a MissingItemIndexException
     * @param errorMessage String provided by user
     */
    public MissingItemIndexException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a formatted string object
     * @return String formatting the error message
     */
    @Override
    public String toString() {
        return String.format("Missing Item Index: missing an index! " +
                "Please enter an index to be marked done!");
    }

}
