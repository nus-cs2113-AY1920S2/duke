package duke;

public class DukeException extends Exception {
    private String errorMessage;
    public DukeException () {
        super ();
    }

    public DukeException (String errorMessage) {
        super (errorMessage);
        System.out.println(errorMessage);
    }
}