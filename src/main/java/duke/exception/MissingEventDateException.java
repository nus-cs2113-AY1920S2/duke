package duke.exception;

public class MissingEventDateException extends DukeException {
    private String errorMsg;

    public MissingEventDateException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}
