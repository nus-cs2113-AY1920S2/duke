package Exceptions;

/**
 * Custom exception for delete command
 */
public class EmptyListException extends Exception {
    public EmptyListException(String errorMessage) {
        super(errorMessage);
    }
}
