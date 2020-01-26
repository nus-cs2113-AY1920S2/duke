package commands;

import common.Messages;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */

public class IncorrectCommand extends Command{

    public final String feedbackToUser;

    public IncorrectCommand(String commandWord) {
        this.feedbackToUser = commandWord;
    }

    @Override
    public CommandResult execute() {
        //for future implement
        //return new CommandResult(feedbackToUser);
        return null;
    }

}
