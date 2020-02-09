package duke.exception;

public class MissingDeadlineDateException extends DukeException {
    private String errorMsg;

    public MissingDeadlineDateException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}
