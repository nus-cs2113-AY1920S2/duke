package exceptions;
// is a checked exception
public class DukeException extends Exception{
    public DukeException(String message) {
        super("A Duke error has occurred! \n\t" +message);
    }

//    public exceptions.DukeException(String message, Throwable cause) {
//        super("A Duke error has occurred! " +message + "\t" +cause);
//    }
}
