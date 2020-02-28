package exceptions;

/**
 * Represents exceptions when index is out of range
 */
public class OutOfRangeException extends DukeExceptions {

    public OutOfRangeException() {
        super();
    }

    /**
     * Returns String format of error message when index is out of range
     * @return String format of error message
     */
    @Override
    public String toString() {
        return " [Error: Index is out of range!]";
    }
}
