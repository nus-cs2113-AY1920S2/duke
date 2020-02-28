package exceptions;

/**
 * Represents exception when date/time/location of task is missing
 */
public class MissingDetailsException extends DukeExceptions {
    public MissingDetailsException() {
        super();
    }

    /**
     * Returns string format of error message when date/time/location of task is missing
     * @return String format of error message
     */
    public String toString() {
        return " [Error: Missing date / time / location]";
    }
}
