package alie.exceptions;

/**
 * Exception for when an invalid file format is detected
 */
public class InvalidFileFormatException extends Exception {
    public static final String INCORRECT_FILE_FORMAT_MSG = "File format is not correct.";

    public InvalidFileFormatException(String errorMsg) {
        super(errorMsg);
    }

    /**
     * Formats the string that is return from the exception thrown
     * @return String with default error message for this error.
     */
    @Override
    public String toString() {
        return ", INVALID FILE FORMAT ERROR: " + super.getMessage();
    }
}
