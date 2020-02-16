package Exceptions;

public class MissingItemIndexException extends DukeException {
    private String errorMessage;

    public MissingItemIndexException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString(){
        return String.format("Missing Item Index: missing an index! " +
                "Please enter an index to be marked done!");
    }

}
