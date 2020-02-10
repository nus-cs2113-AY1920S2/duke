package exceptions;

public class EmptyListException extends DukeException {
    private String error;

    public EmptyListException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Error: %s", this.error);
    }
}