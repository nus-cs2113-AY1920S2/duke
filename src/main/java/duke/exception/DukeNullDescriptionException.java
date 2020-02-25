package duke.exception;

import static duke.util.Constants.TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE;

/**
 * This is an exception class inherited from DukeException class. It is raised if the user inputs a task with empty
 * task description.
 *
 * @author A11riseforme
 */
public class DukeNullDescriptionException extends DukeException{
    /**
     * Default constructor.
     */
    public DukeNullDescriptionException() {
        super(TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
    }
}
