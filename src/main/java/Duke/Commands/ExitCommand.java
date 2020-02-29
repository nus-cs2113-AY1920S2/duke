package Duke.Commands;

import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.Ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        Ui.displayExitMessage();
    }
}
