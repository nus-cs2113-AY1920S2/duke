package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Lists all tasks in the task list to the user.
 */
public class ListCommand extends Command {


    public ListCommand(String fullCommand, String taskType, String args) {
        super(fullCommand, taskType, args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
       list(tasks, ui);
    }

    /**
     * Lists tasks in the task list.
     * @param tasks the task list.
     * @param ui Duke's UI.
     */
    public void list(TaskList tasks, Ui ui) {
        ui.printInfoHead("Here are the matching tasks in your list:");
        for(Task task: tasks){
            int id = tasks.indexOf(task) + 1;
            ui.printOneTask(task, id);
        }
        ui.printOutputTail();
    }
}
