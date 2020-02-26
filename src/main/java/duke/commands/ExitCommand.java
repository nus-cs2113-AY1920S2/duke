package duke.commands;

import duke.data.task.TaskList;
import duke.ui.TextUi;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasklist) {
        super.isExit = true;
        TextUi.printExit();
    }
}
