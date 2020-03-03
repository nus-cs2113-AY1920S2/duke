package duke.command;

public class FalseCommand extends Command {

    public final String feedback;

    public FalseCommand(String feedbackToUser) {
        this.feedback = feedbackToUser;
    }

    @Override
    public CommandOption execute() {
        return new CommandOption(feedback);
    }
}
