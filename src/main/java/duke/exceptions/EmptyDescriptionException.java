package duke.exceptions;

public class EmptyDescriptionException extends Exception {
    private String command;
    
    public EmptyDescriptionException(String command) {
        this.command = command;
    }
    
    public String getCommand() {
        return command;
    }
}
