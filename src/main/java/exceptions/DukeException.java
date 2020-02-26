package exceptions;
public class DukeException extends Exception{
    public DukeException(String message) {
        super("A Duke error has occurred! \n\t" +message);
    }
}
