package duke.exceptions;

public class EmptyDescriptionException extends ChatboxException {
    private String command;
    
    public EmptyDescriptionException(String command) {
        this.command = command;
    }
    
    public String getCommand() {
        return command;
    }
}
