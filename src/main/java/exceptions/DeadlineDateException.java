public class DeadlineDateException extends DukeException {
    private String errorMsg;

    public DeadlineDateException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}