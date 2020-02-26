package duke.command;

import duke.common.DukeException;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int deleteCount;

    public DeleteCommand(int deleteCount) {
        // The array index will be actual count minus one
        this.deleteCount = deleteCount - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException{
        if (deleteCount + 1 > tasks.size())
            throw new DukeException("\tThere is no task " + (deleteCount + 1) + ". Please reconsider the index.");
        ui.showDeleteMessage(tasks.deleteTask(deleteCount),tasks);
        storage.write(tasks);
    }
}
