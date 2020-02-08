package commands;


/** Clears the Task List */

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "Clears task list permanently.\n"
            + "    Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Task List has been cleared!";

    @Override
    public CommandResult execute() {
        duke.clear();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
