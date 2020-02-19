package exceptions;

public class EventDateException extends DukeException {
    private String error;

    public EventDateException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("Error: %s", this.error);
    }
}