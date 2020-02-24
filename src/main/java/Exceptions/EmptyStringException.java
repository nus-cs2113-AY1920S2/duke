package Exceptions;

/**
 * Represents exceptions when all fields of deadline and event tasks are missing
 */
public class EmptyStringException extends DukeExceptions {
    public EmptyStringException() {
        super();
    }

    /**
     * Returns String format of error message for EmptyStringException
     * @return String format of error message
     */
    @Override
    public String toString() {
        return " [Error: Empty description(s) / date or time or location / placeholder]";
    }
}
