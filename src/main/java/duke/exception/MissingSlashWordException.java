package duke.exception;

/**
 * The custom exception thrown when no slash word is given despite expecting it
 */
public class MissingSlashWordException extends Exception {
    public MissingSlashWordException(String s) {
        super(s);
    }
}
