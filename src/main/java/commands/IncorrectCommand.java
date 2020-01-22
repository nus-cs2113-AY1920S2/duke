package commands;

import common.Messages;

public class IncorrectCommand extends Command{

    public final String feedbackToUser;

    public IncorrectCommand(String commandWord) {
        this.feedbackToUser = commandWord;
    }

    @Override
    public CommandResult execute() {
        echo();
        //for future implement
        //return new CommandResult(feedbackToUser);
        return null;
    }

    private void echo(){
        System.out.println(Messages.DIVIDER);
        System.out.println("  "+feedbackToUser);
        System.out.println(Messages.DIVIDER);
    }
}
