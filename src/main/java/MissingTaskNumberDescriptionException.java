public class MissingTaskNumberDescriptionException extends DukeException {
    private String errorMsg;

    public MissingTaskNumberDescriptionException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}
