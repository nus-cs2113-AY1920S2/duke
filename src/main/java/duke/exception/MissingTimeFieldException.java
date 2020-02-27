package duke.exception;

/**
 * The custom exception thrown when the task is missing the date/time information when it is supposed/expected to have
 * it
 * <p></p>
 * <p>As long as there is a <code>"/"</code> is provided, it is assumed that a date/time will be supplied. If there is
 * no <code>"/"</code>, then it is assumed the task do not have a date/time</p>
 */
public class MissingTimeFieldException extends Exception {
    public MissingTimeFieldException(String s) {
        super(s);
    }
}
