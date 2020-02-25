package duke.storage;

import duke.commands.Command;
import duke.commands.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class loader extends Command {
    public loader(String pastListItem) {
        super(pastListItem);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    @Override
    public boolean isExit() {return false;}

}
