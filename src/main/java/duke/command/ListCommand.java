package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * a command class that executes the operation of listing all tasks in task list
 */
public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.listTask(ui);
    }
    
}
