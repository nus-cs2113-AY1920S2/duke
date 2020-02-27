package duke.exception;
/**
 * The custom exception thrown when for the <code>EVENT</code> and <code>DEADLINE</code> task the slash word provided
 * is wrong
 */
public class WrongSlashWordException extends Exception{
    public WrongSlashWordException(String s) {
        super(s);
    }
}
