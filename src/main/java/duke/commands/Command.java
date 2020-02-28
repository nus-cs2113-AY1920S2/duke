package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public abstract class Command {

    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public abstract void execute(TaskList tasklist, UI ui, Storage storage);

}
