package Exceptions;

/**
 * Represents exception when index is not a number
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
