package duke.exceptions;

public class BadLineFormatException extends Exception {
    public BadLineFormatException(String errorMessage){
        super(errorMessage);
    }
}
