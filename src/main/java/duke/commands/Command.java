package duke.commands;

import duke.exception.InvalidCommandException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Command {

    public boolean isExit = false;

    protected TaskList tasks;

    public void setList(TaskList tasks) {
        this.tasks = tasks;
    }

    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) throws InvalidCommandException {
        throw new InvalidCommandException();
    }

}
