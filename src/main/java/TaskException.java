
public class TaskException extends Exception {

    public TaskException (String e) {
        super(e);
    }

    @Override
    public String toString() {
        return this.getLocalizedMessage();
    }

}
