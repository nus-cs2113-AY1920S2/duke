package Duke;

public class DukeException extends Exception {
    protected static String errorMessage;

    public DukeException() {
    }

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage(){
        return errorMessage;
    }
}
