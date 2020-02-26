package duke.command;

import duke.common.DukeException;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command{
    private int doneCount;

    public DoneCommand(int doneCount) {
        // The array index will be actual count minus one
        this.doneCount = doneCount - 1;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if(doneCount + 1 > tasks.size()) {
            throw new DukeException("\tThere is no task " + (doneCount + 1) + ". Please reconsider the index.");
        }
        tasks.getATask(this.doneCount).markAsDone();
        ui.showMarkAsDoneMessage(tasks,this.doneCount);
        storage.write(tasks);
    }
}
