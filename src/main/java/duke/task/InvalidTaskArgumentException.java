package duke.task;

public class InvalidTaskArgumentException extends IllegalArgumentException {
    
    public InvalidTaskArgumentException(String message) {
        super(message);
    }
}
