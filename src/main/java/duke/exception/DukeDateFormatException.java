package duke.exception;

import static duke.util.Constants.TASK_DATE_FORMAT_ERROR_MESSAGE;

public class DukeDateFormatException extends DukeException {
    public DukeDateFormatException() {
        super(TASK_DATE_FORMAT_ERROR_MESSAGE);
    }
}
