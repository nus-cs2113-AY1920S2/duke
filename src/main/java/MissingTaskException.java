public class MissingTaskException extends DukeException {

    private String errorMsg;

    public MissingTaskException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return String.format("Sorry! %s", this.errorMsg);
    }


}
