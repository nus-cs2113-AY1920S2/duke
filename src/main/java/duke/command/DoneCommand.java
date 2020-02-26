package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * a command class that executes the operation of marking a task as done in task list
 */
public class DoneCommand extends Command {

    private int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * execution method of done command
     * @param tasks the object stores task list and can do operations on the task list
     * @param ui the object that interact with user
     * @throws DukeException if the task# entered by the user is out of bound
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            String description = tasks.markTask(taskNo);
            ui.printFormat(Ui.DONE_TASK + description);
        } catch (IndexOutOfBoundsException e)  {
            throw new DukeException("the taskNo is not within the range");
        }
    }

}
