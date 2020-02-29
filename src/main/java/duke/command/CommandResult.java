package duke.command;


public class CommandResult {

    private String userFeedback;

    public CommandResult () {
        this.userFeedback = "";
    }

    public CommandResult (String userFeedback) {
        this.userFeedback = userFeedback;
    }


    public String getUserFeedback () {
        return userFeedback;
    }

}
