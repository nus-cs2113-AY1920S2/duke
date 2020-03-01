package duke;

import duke.Storage;
import duke.TaskList;

import java.io.IOException;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    /** An abstract method to execute a command, given a task and a storage to save any changes.
     * @param storage    the storage to save any changes.
     * @param tasks      the user's TaskList.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}