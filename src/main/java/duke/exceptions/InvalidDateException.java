package src.main.java.duke.exceptions;

public class InvalidDateException extends DukeException {
    public String getMessage() { return "Please re-enter command with the time and/or date."; }
}
