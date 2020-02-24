package duke.exception;

import static duke.util.Constants.FIVE_SPACES;

public class DukeException extends Exception {

    public DukeException() {
        super();
    }

    public DukeException(String ErrorMsg) {
        super(FIVE_SPACES + ErrorMsg);
    }

}
