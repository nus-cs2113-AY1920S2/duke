package duke.exception;

public class DukeException extends Exception {
    
    private static final String DIVIDER = "____________________________________________________________";
    private static final String LS = System.lineSeparator();
    
    public DukeException(String message) {
        super(message);
    }
    
    @Override
    public String toString() {
        return  DIVIDER + LS +  getMessage() + LS + DIVIDER;
    }
}
