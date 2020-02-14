package duke.exceptions;

public class BadFileFormatException extends Exception {
    public BadFileFormatException(String errorMessage){
        super("Bad file format:" + System.lineSeparator() + errorMessage);
    }
}
