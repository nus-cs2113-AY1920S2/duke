package duke.exception;

import static duke.util.Constants.TASK_DATE_EMPTY_ERROR_MESSAGE;

/**
 * This is an exception class inherited from DukeException class. It is raised if user input a deadline or event task
 * with empty date.
 *
 * @author A11riseforme
 */
public class DukeNullDateException extends DukeException {
    /**
     * Default constructor.
     */
    public DukeNullDateException() {
        super(TASK_DATE_EMPTY_ERROR_MESSAGE);
    }
}
