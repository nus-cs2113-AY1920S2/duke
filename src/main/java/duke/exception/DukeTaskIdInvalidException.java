package duke.exception;

import static duke.util.Constants.TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE;

/**
 * This is an exception class inherited from DukeException class. It is raised if the task id provided by the user
 * when calling a `delete` or `done` command is invalid.
 *
 * @author A11riseforme
 */
public class DukeTaskIdInvalidException extends DukeException {
    /**
     * Default constructor.
     */
    public DukeTaskIdInvalidException() {
        super(TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE);
    }
}
