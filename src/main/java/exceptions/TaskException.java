public class TaskException extends DukeException {

    private String errorMsg;

    public TaskException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}