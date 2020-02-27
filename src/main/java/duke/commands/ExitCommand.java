package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;

import static duke.utils.Constants.BYE_COMMAND;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException {
        this.isExit = true;
        this.command = BYE_COMMAND;
        ui.displayExitMessage();
    }
}
