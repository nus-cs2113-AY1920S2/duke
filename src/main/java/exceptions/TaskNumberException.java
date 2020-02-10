public class TaskNumberException extends DukeException {
    private String errorMsg;

    public TaskNumberException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}