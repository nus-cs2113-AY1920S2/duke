package exceptions;

/**
 * Custom Exceptions that throws when user marks a Task that is already done
 */
public class TaskAlreadyDoneException extends DukeException {
    private String errorMessage;

    /**
     * Constructs a TaskAlreadyDoneException object
     * @param errorMessage String provided by user
     */
    public TaskAlreadyDoneException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a formatted string
     * @return String formatting the error message
     */
    @Override
    public String toString() {
        return String.format("Task has already been marked done before! Try another task!");
    }
}