package Duke.Exception;

import Duke.Library.ErrorMessage;

public class DukeDateParseException extends Exception {
    public DukeDateParseException() {
        super(ErrorMessage.INVALID_FORMAT);
    }
}
