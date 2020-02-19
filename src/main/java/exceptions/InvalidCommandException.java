package exceptions;

public class InvalidCommandException extends DukeException {
    private String error;

    public InvalidCommandException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Error: %s", this.error);
    }
}
