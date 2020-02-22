package Exceptions;

public class TaskAlreadyDoneException extends DukeException {
    private String errorMessage;

    public TaskAlreadyDoneException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return String.format("Task has already marked done before! Try another task!");
    }
}
