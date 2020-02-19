package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class DoneCommand extends Command {
    public DoneCommand(String fullCommand, String taskType, String args) {
        super(fullCommand, taskType, args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskToBeMarked = markAsDone(tasks);
        ui.printTask(taskToBeMarked, "marked");
        storage.save(tasks);
    }

    public Task markAsDone(TaskList tasks) throws DukeException {
        if(super.args.length() == 0){
            throw new DukeException("Please enter a number!");
        }
        int taskID = Integer.parseInt(super.args);

        /* Exit if enter a wrong task id */
        boolean isWrongID = taskID > tasks.size() || taskID < 1;
        if (isWrongID) {
            throw new DukeException("Your input number is out of range!");
        }

        Task taskToBeMarked = tasks.get(taskID - 1);
        taskToBeMarked.markAsDone();

        return taskToBeMarked;
    }

}
