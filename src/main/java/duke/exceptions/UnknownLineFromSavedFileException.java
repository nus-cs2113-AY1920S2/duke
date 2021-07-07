package src.main.java.duke.exceptions;

public class UnknownLineFromSavedFileException extends DukeException {
    public String getMessage() { return "Unknown line read from Save File"; }
}
