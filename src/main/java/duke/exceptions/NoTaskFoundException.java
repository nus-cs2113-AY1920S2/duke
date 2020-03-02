package src.main.java.duke.exceptions;

public class NoTaskFoundException extends DukeException {
    public String getMessage(){
        return "No matching task found in your list";
    }
}
