package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class Todo extends Command {
    public Todo (String input) throws DukeException {
        super("[T][✗] " + input.trim());
        if (input.matches("\\s*")) {
            throw new DukeException(1,"todo");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this);
        ui.showListIncrementOutput(command,tasks.list.size());
        storage.updateListDataOnDisk(tasks.list);
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
