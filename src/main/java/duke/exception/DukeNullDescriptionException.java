package duke.exception;

import static duke.util.Constants.TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE;

public class DukeNullDescriptionException extends DukeException{
    public DukeNullDescriptionException() {
        super(TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
    }
}
