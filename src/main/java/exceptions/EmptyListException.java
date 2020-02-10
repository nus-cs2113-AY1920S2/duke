public class EmptyListException extends DukeException {
    private String errorMsg;

    public EmptyListException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }
}