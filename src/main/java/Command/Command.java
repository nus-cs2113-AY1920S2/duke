package Command;

import Storage.Storage;
import Task.TaskList;
import UI.Ui;

/**
 * Represents an executable command
 */
public abstract class Command {

    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;

    /**
     * Called to check if exit command is given
     * @return condition whether program should continue looping or exit
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Supplies the objects other commands will call upon
     *
     * @param tasks  current instance of tasks and corresponding tasklist methods
     * @param storage instance of storage object
     * @param ui instance of user interaction object
     */
    public void setCommandVariables(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes user command processed by parser
     */
    public abstract void execute();

}
