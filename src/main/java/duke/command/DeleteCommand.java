package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class DeleteCommand extends Command {

    private int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException{
        try {
            String description = tasks.deleteTask(taskNo);
            ui.printFormat(Ui.DELETE_TASK + description);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The taskNo is not within the range");
        }
    }
}
