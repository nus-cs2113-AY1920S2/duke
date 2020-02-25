package Duke.Parser;

import Duke.Commands.Command;
import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.Ui.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        Ui.displayHelpMenu();
    }
}
