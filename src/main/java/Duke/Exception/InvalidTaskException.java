package Duke.Exception;

/**
 * The custom exception thrown when the task supplied is not a valid task
 */
public class InvalidTaskException extends Exception {
    public InvalidTaskException(String s) {
        super(s);
    }
}
