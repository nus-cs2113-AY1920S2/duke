package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import static duke.common.Messages.CLEAR_MESSAGE;

public class ClearCommand extends Command {
    public static final String COMMAND_PHRASE = "clear";
    public static final String COMMAND_USAGE = COMMAND_PHRASE
            + System.lineSeparator() + "-Clears all tasks in the list";

    @Override
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {
        String feedback = CLEAR_MESSAGE;
        tasks.clearList();
        storage.performCleanup(textUi);
        return new CommandResult(feedback);
    }
}
