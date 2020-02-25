package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;

public class NonExistent extends Command {
    public NonExistent() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showNonExistentInputError();
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
