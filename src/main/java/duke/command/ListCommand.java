package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class ListCommand extends Command {


    public ListCommand(String fullCommand, String taskType, String args) {
        super(fullCommand, taskType, args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
       list(tasks);
    }

    public void list(TaskList tasks) {
        System.out.println(CUTTING_LINE);
        System.out.println("\tHere are the tasks in your list:");
        for (Task task : tasks) {
            int taskID = tasks.indexOf(task) + 1;
            System.out.println("\t" + taskID + "." + task.toString());
        }
        System.out.println(CUTTING_LINE);
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }
}
