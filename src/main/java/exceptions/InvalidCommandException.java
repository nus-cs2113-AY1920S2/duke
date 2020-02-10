public class InvalidCommandException extends DukeException {
    private String errorMsg;

    public InvalidCommandException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry, %s", this.errorMsg);
    }
}
