package src.main.java.duke.exceptions;

public class InvalidDoneException extends DukeException {
    public String getMessage() { return "Unable to process done statement"; }
}
