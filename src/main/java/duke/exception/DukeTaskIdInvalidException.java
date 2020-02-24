package duke.exception;

import static duke.util.Constants.TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE;

public class DukeTaskIdInvalidException extends DukeException{
    public DukeTaskIdInvalidException() {
        super(TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE);
    }
}
