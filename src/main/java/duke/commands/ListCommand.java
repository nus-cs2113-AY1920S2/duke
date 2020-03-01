package duke.commands;

import static duke.ui.Messages.LIST_MESSAGE;

/**
 * <h3>List Command</h3>
 * A <b>Command</b> to show the <b>Task List</b> to the user.
 * @see Command
 * @see duke.data.TaskList
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String FORMAT = "list";

    /**
     * Executes the <b>List Command</b> to show the <b>Task List</b> to the user.
     *
     * @return The <b>Command Result</b> of the execution
     * @see duke.data.TaskList
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(LIST_MESSAGE, true);
    }
}
