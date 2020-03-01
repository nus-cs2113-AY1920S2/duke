package src.main.java.duke.exceptions;

public class AlreadyDoneException extends DukeException {
    public String getMessage() { return "Task has already been completed."; }
}
