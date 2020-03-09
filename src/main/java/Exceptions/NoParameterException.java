package Exceptions;

/**
 * Custom exception when no parameter / whitespace is detected for commands
 */
public class NoParameterException extends Exception{
    public NoParameterException(String errorMessage) {
        super(errorMessage);
    }
}
