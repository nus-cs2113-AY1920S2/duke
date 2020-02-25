package duke.exception;

import static duke.util.Constants.FILE_OPERATION_IO_ERROR_MESSAGE;

/**
 * This is an exception class inherited from DukeException class. It is raised if error occurs when trying to
 * accessing the data file.
 *
 * @author A11riseforme
 */
public class DukeLoadingException extends DukeException {
    /**
     * Default constructor.
     */
    public DukeLoadingException() {
        super(FILE_OPERATION_IO_ERROR_MESSAGE);
    }
}
