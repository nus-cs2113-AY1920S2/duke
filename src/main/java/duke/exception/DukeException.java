package duke.exception;

public class DukeException extends Exception {

    public DukeException () {
        super("Error");
    }

    public DukeException (String e) {
        super(e);
    }

    @Override
    public String toString() {
        return this.getLocalizedMessage();
    }
}
