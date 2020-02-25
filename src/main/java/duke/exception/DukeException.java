package duke.exception;

import static duke.util.Constants.FIVE_SPACES;

/**
 * The parent exception class that handles the general exception
 *
 * @author A11riseforme
 */
public class DukeException extends Exception {

    /**
     * Default constructor
     */
    public DukeException() {
        super();
    }

    /**
     * Constructor with one argument as the error message.
     *
     * @param ErrorMsg the message to be shown when exception is raised.
     */
    public DukeException(String ErrorMsg) {
        super(FIVE_SPACES + ErrorMsg);
    }

}
