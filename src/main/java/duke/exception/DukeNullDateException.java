package duke.exception;

public class DukeNullDateException extends DukeException {
    public DukeNullDateException() {
        super("date is not provided");
    }
}
