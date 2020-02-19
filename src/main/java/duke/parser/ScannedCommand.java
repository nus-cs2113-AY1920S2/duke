package duke.parser;

public class ScannedCommand {
    private String userCommand;
    private String userParams;

    public ScannedCommand(String userCommand, String userParams) {
        this.userCommand = userCommand;
        this.userParams = userParams;
    }

    public String getUserCommand() {
        return userCommand;
    }

    public String getUserParams() {
        return userParams;
    }
}
