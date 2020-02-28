package exceptions;

/**
 * Represents the exception when description is missing
 */
public class MissingDescriptionsException extends DukeExceptions {

    public MissingDescriptionsException() {
        super();
    }

    /**
     * Returns string format of error message when description field is missing
     * @return String format of error message
     */
    @Override
    public String toString() {
        return " [Error: Missing description(s)]";
    }
}
