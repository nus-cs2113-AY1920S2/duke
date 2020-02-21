package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Displays all available commands with their format and usage.
 */
public class HelpCommand extends Command{

    /** Format of the command */
    public static final String COMMAND_PHRASE = "help";

    /** Usage of the command */
    public static final String COMMAND_USAGE = COMMAND_PHRASE + System.lineSeparator()
            + "-Displays all commands and their input format";

    /**
     * Returns a <code>CommandResult</code> with feedback to the user initialised.
     *
     * @param tasks <code>TaskList</code> object that the <code>Command</code> will execute on.
     * @param textUi <code>Ui</code> object that is being used to display output to the user.
     * @param storage <code>Storage</code> object that is able to access tasks saved in memory.
     * @return <code>CommandResult</code>.
     */
    @Override
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {
        String feedback = HelpCommand.COMMAND_USAGE + "\n\n"
                + ListCommand.COMMAND_USAGE + "\n\n"
                + DoneCommand.COMMAND_USAGE + "\n\n"
                + DeleteCommand.COMMAND_USAGE + "\n\n"
                + ClearCommand.COMMAND_USAGE + "\n\n"
                + ByeCommand.COMMAND_USAGE + "\n\n"
                + AddTodoCommand.COMMAND_USAGE + "\n\n"
                + AddEventCommand.COMMAND_USAGE + "\n\n"
                + AddDeadlineCommand.COMMAND_USAGE + "\n";
        return new CommandResult(feedback);
    }
}
