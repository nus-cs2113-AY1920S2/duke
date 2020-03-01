package exceptions;

/**
 * Represents exception when search query is missing for find command
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
