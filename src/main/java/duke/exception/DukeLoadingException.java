package duke.exception;

public class DukeLoadingException extends DukeException {
    public DukeLoadingException(String dataFileName) {
        super(dataFileName);
    }
}
