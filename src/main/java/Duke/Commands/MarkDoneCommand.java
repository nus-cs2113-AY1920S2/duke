package Duke.Commands;

import Duke.Exception.DukeException;
import Duke.Library.ErrorMessage;
import Duke.Storage.Storage;
import Duke.Task.Task;
import Duke.Ui.Ui;

public class MarkDoneCommand extends Command {
    private int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        try {
            Task task = storage.getTasks().get(index);
            task.setDone(true);
            ui.displayMarkDone(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ErrorMessage.OUT_OF_BOUNDS);
        }
        storage.write();
    }
}
