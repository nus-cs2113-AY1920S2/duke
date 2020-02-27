package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.exceptions.ChatboxException;

public abstract class Command {
    protected boolean isExit = false;
    protected String command;
    
    public boolean isExit() {
        return this.isExit;
    }
    
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatboxException;
    
    public String getCommand() {
        return this.command;
    }
}
