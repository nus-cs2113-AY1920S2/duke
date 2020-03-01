package exceptions;

/**
 * Represents the exception when index is missing from done or delete command
 */
public class MissingIndexException extends DukeExceptions {
    public MissingIndexException() {
        super();
    }

    /**
     * Returns string format of error message when index is missing
     * @return String format of error message
     */
    @Override
    public String toString() {
        return " [Error: Missing Index]";
    }
}
