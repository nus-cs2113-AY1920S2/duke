package duke.command;

/**
 * A class representing the result from the execution of a command.
 */
public class CommandResult {

    /** Message to be displayed to the user based on the execution of a command */
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
