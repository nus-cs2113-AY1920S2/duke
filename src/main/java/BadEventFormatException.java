public class BadEventFormatException extends BadTaskFormatException {
    public BadEventFormatException(String message) {
        super("Bad format for event: " + message);
    }
}
