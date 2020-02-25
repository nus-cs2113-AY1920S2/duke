package duke.exception;

import static duke.util.Constants.DATA_FILE_NAME;

/**
 * This is an exception class inherited from DukeException class. It is raised if error occurs when trying to
 * accessing the data file.
 *
 * @author A11riseforme
 */
public class DukeWritingException extends DukeException {
    /**
     * Default constructor.
     */
    public DukeWritingException() {
        super(DATA_FILE_NAME);
    }
}
