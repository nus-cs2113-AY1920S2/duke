package exceptions;

public class MissingDescriptionException extends DukeException{
    private String errorMessage;

    // default constructor
    public MissingDescriptionException(){
    }

    public MissingDescriptionException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString(){
        return String.format("Missing Description: '%s' missing a description." +
                " Please enter a description!", errorMessage);
    }
}
