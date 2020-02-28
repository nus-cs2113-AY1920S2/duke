package alie.exceptions;

/**
 * Exception for when an invalid command is detected
 */
public class InvalidCmdException extends Exception {
    //exceptions specific to ALIE
    public InvalidCmdException (String errorMsg) {
        super(errorMsg);
    }

    /**
     * Formats the string that is return from the exception thrown
     * @return String with default error message for this error.
     */
    @Override
    public String toString() {
        return "INVALID CMD ERROR: " + super.getMessage();
    }
}
