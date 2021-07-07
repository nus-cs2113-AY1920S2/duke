package src.main.java.duke.exceptions;

public class InvalidDeleteException extends DukeException {
    public String getMessage() { return "Unable to process delete statement"; }
}
