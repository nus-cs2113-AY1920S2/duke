package exceptions;

public class TaskException extends DukeException {
    private String error;

    public TaskException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Error: %s", this.error);
    }
}