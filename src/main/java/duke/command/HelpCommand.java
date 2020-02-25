package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/* a command class that executes the operation of printing out
all commands of Duke and corresponding format(a simple User Guide) */
public class HelpCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printUserGuide();
    }
}
