package duke.exception;

import static duke.util.Constants.TASK_DATE_EMPTY_ERROR_MESSAGE;

public class DukeNullDateException extends DukeException {
    public DukeNullDateException() {
        super(TASK_DATE_EMPTY_ERROR_MESSAGE);
    }
}
