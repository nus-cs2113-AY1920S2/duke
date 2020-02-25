package duke.command;

import duke.Parser;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class DoneCommand extends Command {

    private int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            String description = tasks.markTask(taskNo);
            ui.printFormat(Ui.DONE_TASK + description);
        } catch (IndexOutOfBoundsException e)  {
            throw new DukeException("the taskNo is not within the range");
        }
    }

}
