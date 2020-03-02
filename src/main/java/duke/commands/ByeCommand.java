package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public static final String BYE_COMMAND_NAME = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
        System.exit(0);
    }
}
