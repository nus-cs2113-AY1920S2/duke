package duke.exceptions;

public class DeadlineTaskException extends DukeException {
    private String error;

    public DeadlineTaskException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Error: %s", this.error);
    }
}