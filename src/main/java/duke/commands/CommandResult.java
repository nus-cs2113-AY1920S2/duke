package duke.commands;

public class CommandResult {
    private final String message;
    private final boolean isPrintList;

    public CommandResult(String message) {
        this.message = message;
        isPrintList = false;
    }

    public CommandResult(String message, boolean isPrint) {
        this.message = message;
        isPrintList = isPrint;
    }

    public String getMessage() {
        return message;
    }

    public boolean getPrintStatus() {
        return isPrintList;
    }
}
