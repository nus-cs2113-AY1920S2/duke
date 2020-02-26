package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * a command class that executes the operation of quiting from Duke
 */
public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.exit();
    }

}
