public class TaskDescriptionException extends DukeException {
    private String errorMsg;

    public TaskDescriptionException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}