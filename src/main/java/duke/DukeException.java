package duke;
/**
 * Exception class of the Duke project
 * prints a error message
 */
public class DukeException extends Exception {
    private String message;

    public DukeException(String message) {
        this.message = message;
        System.out.println(message);
    }
}
