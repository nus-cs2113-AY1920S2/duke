package duke.exceptions;

public class DeadlineDateException extends DukeException {
    private String error;

    public DeadlineDateException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Error: %s", this.error);
    }
}