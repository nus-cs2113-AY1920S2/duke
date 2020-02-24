package Exceptions;

/**
 * Represents exception when parameter of task is missing
 */
public class MissingParameterException extends DukeExceptions {

    public MissingParameterException() {
        super();
    }

    /**
     * Returns String format of error message when parameter is missing
     * @return String format of error message
     */
    @Override
    public String toString() {
        return " [Error: Parameter is missing!]";
    }
}
