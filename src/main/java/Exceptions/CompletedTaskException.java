package exceptions;

/**
 * Represents exception when user tries to mark a completed task as done
 */
public class CompletedTaskException extends DukeExceptions {

    public CompletedTaskException() {
        super();
    }

    /**
     * Returns string format of error message for CompletedTaskException
     * @return String format of error message
     */
    @Override
    public String toString() {
        return " [Warning: Task has already been completed!]";
    }
}
