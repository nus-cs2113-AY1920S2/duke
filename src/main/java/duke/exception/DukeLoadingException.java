package duke.exception;

import static duke.util.Constants.FILE_OPERATION_IO_ERROR_MESSAGE;

public class DukeLoadingException extends DukeException {
    public DukeLoadingException() {
        super(FILE_OPERATION_IO_ERROR_MESSAGE);
    }
}
