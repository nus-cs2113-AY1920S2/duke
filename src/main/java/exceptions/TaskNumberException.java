package exceptions;

public class TaskNumberException extends DukeException {
    private String error;

    public TaskNumberException(String errorMsg) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Error: %s", this.error);
    }
}