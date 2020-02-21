package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command{

    public static final String COMMAND_PHRASE = "help";

    public static final String COMMAND_USAGE = COMMAND_PHRASE + System.lineSeparator()
            + "-Displays all commands and their input format";


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
