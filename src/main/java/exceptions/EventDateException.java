public class EventDateException extends DukeException {
    private String errorMsg;

    public EventDateException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}