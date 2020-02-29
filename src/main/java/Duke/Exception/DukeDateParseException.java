package Duke.Exception;

import Duke.Library.ErrorMessage;

/**
 * Represents a DateTime error during operation of duke.Duke.
 */
public class DukeDateParseException extends Exception {
    public DukeDateParseException() {
        super(ErrorMessage.INVALID_FORMAT);
    }
}
