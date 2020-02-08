package Exceptions;

public class UnknownCommandException extends DukeException{
    private String errorMessage;

    public UnknownCommandException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return String.format("Unknown Command Found: '%s' is not a valid command!" +
                " Please enter a valid command!", errorMessage);
    }
}
