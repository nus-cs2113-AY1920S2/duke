package commands;
import exception.DukeException;
import task.TaskList;
import ui.Ui;
import storage.Storage;
public class Command {

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child classes");
    }

    public boolean isExit() {
        return false;
    }
}
