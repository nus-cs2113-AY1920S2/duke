package duke.command;

import duke.task.TaskManager;

public class ListCommand extends Command {

    public ListCommand (TaskManager manager) {
        super(manager, null);
    }

    /**
     *  Prints the tasks that are currently in the list
     */
    public void execute () {
        taskManager.listTasks();
    }

}
