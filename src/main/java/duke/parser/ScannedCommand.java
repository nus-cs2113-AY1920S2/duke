package duke.parser;

/**
 * Object that holds the command input by the user, along with any optional parameters.
 */
public class ScannedCommand {

    /** Command input by the user. */
    private String userCommand;

    /** Parameters input by the user. */
    private String userParams;

    /**
     * Constructs a ScannedCommand object using the user's input.
     *
     * @param userCommand Command input by the user.
     * @param userParams Parameters input by the user.
     */
    public ScannedCommand(String userCommand, String userParams) {
        this.userCommand = userCommand;
        this.userParams = userParams;
    }

    /**
     * Returns the command input by the user.
     *
     * @return The user command.
     */
    public String getUserCommand() {
        return userCommand;
    }

    /**
     * Returns the parameters input by the user.
     *
     * @return The user parameters.
     */
    public String getUserParams() {
        return userParams;
    }
}
