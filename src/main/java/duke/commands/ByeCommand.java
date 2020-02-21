package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import static duke.common.Messages.BYE_MESSAGE;

public class ByeCommand extends Command {

    public static final String COMMAND_PHRASE = "bye";
    public static final String COMMAND_USAGE = COMMAND_PHRASE
            + System.lineSeparator() + "-Exits the application";

    @Override
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {
        this.isExit = true;
        return new CommandResult(BYE_MESSAGE);
    }
}
