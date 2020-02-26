package duke.commands;

import duke.common.Messages;
import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.ui.TextUi;

public class DoneCommand extends Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist) throws DukeException {
        if (index >= tasklist.size()){
            throw new DukeException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
        }
        tasklist.get(index).taskDone();
        TextUi.printDone(tasklist, index);
    }
}
