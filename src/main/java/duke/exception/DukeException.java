package duke.exception;

/**
 * Signals that some exceptions when Duke runs.
 */
public class DukeException extends Exception {
    private String message;

    public DukeException(){
        super();
    }

    public DukeException(String message){
        super(message);
        this.message = message;
    }

}
