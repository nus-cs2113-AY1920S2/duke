package commands;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class AddCommand extends Command {

    AddCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child AddCommand classes");
    }
}
