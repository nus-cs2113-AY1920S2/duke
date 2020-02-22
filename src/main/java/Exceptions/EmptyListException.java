package Exceptions;

public class EmptyListException extends DukeException {
    private String errorMessage;

    public EmptyListException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return String.format("Empty List: %s Please add an item!", errorMessage);
    }
}
