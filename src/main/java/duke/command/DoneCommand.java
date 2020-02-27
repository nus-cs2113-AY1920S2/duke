package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Marks a task as done using it's index from the task list.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public DoneCommand(String fullCommand, String taskType, String args) {
        super(fullCommand, taskType, args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task taskToBeMarked = markAsDone(tasks);
        ui.printTask(taskToBeMarked, "marked");
        storage.save(tasks);
    }

    /**
     * Marks a task as done.
     * @param tasks the task list.
     * @return the marked task.
     * @throws DukeException if index is out of range.
     */
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
