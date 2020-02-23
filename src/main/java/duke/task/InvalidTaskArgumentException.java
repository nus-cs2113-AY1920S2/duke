package duke.task;

/**
 * Represents an exception pertaining to invalid task arguments.
 * This exception is thrown when arguments of a task are not in the correct 
 * format specified by the Task constructor.
 * 
 */
public class InvalidTaskArgumentException extends IllegalArgumentException {
    
    public InvalidTaskArgumentException(String message) {
        super(message);
    }
}
