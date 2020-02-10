public class DeadlineTaskException extends DukeException {

    private String errorMsg;

    public DeadlineTaskException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry, %s", this.errorMsg);
    }
}