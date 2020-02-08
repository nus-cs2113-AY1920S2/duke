public class DukeException extends Exception{
    // Exits program with -1
    public DukeException(String errorMessage){
        super(errorMessage);
    }

    public DukeException() {
    }
}
