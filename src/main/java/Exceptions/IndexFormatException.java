package exceptions;

/**
 * Represents exception when index is not a number for done and delete commands
 */
public class IndexFormatException extends DukeExceptions {

    public IndexFormatException() {
        super();
    }

    /**
     * Returns string format of error message when index is not a number
     * @return String format of error message
     */
    @Override
    public String toString() {
        return " [Error: Index is not a number!]";
    }
}
