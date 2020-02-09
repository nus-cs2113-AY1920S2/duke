package duke.exception;

public class MissingTaskNumberException extends DukeException {
    private String errorMsg;

    public MissingTaskNumberException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}
