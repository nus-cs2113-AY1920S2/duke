package Command;

import Storage.Storage;
import Task.TaskList;
import UI.Ui;

public abstract class Command {

    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;

    public boolean isExit() {
        return false;
    }

    public void setCommandVariables(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public abstract void execute();

}
