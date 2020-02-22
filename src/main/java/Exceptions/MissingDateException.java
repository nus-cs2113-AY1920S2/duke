package Exceptions;

public class MissingDateException extends DukeException {
    private String errorMessage;

    public MissingDateException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return String.format("Missing Date: '%s' is missing a date! " +
                "Please enter a date!", errorMessage);
    }
}
