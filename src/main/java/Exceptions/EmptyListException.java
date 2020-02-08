package Exceptions;

public class EmptyListException extends DukeException {
    private String errorMessage;

    public EmptyListException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString(){
        return String.format("Empty List: It seems that the list is empty! Please add an item!");
    }
}
