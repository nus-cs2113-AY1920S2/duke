package duke;

public class BadFileFormatException extends Exception {
    public BadFileFormatException(String errorMessage){
        super("Bad file format: " + errorMessage);
    }
}
