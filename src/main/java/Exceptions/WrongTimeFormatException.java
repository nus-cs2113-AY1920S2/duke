package Exceptions;

public class WrongTimeFormatException extends Exception {
    public WrongTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}
