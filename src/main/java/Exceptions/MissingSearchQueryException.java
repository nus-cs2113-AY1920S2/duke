package Exceptions;

/**
 * Represents exception when search query is missing
 */
public class MissingSearchQueryException extends DukeExceptions {
    public MissingSearchQueryException() {
        super();
    }

    /**
     * Returns String format of error message when search query is missing
     * @return String format of error message
     */
    @Override
    public String toString() {
        return " [Error: Missing Search Query!]";
    }
}
