package duke;

public class InvalidCommandException extends Exception {
    InvalidCommandException() {
    }
    InvalidCommandException(String s) {
        super(s);
    }
}
