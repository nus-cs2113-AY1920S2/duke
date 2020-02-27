package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class Todo extends Command {

    /**
     * Constructor that specifies user input.
     * @param input the user input string
     * @throws DukeException If command is incomplete or empty
     */
    public Todo (String input) throws DukeException {
        super("[T][✗] " + input.trim());
        if (input.matches("\\s*")) {
            throw new DukeException(1,"todo");
        }
    }

    /**
     * @param tasks     the tasks that will be augmented
     * @param ui        the messages that will be displayed
     * @param storage   the storage to be added into
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this);
        ui.showListIncrementOutput(command,tasks.list.size());
        storage.updateListDataOnDisk(tasks.list);
    }

    /**
     * @return false, since this is not a "bye" command.
     */
    @Override
    public boolean isExit(){
        return false;
    }

}
