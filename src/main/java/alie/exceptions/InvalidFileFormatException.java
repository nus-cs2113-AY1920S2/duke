package alie.exceptions;

public class InvalidFileFormatException extends Exception {
    public InvalidFileFormatException(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String toString() {
        return ", INVALID FILE FORMAT ERROR: " + super.getMessage();
    }
}
