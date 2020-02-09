public class UnknownInputException extends DukeException {

    private String errorMsg;

    public UnknownInputException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}
