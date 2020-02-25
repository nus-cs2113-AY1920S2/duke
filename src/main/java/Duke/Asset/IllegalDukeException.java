package Duke.Asset;
/**
 * This is an extension of Exception that is unique to Duke.
 */
public class IllegalDukeException extends Exception{
    public IllegalDukeException(String message){
        super(message);
    }
}
