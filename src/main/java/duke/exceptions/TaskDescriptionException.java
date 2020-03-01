package duke.exceptions;

public class TaskDescriptionException extends DukeException {
    private String error;

    public TaskDescriptionException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Error: %s", this.error);
    }
}