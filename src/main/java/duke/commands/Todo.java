package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Extension of <code>Command</code> class specifying the <code>todo</code> command.
 */
public class Todo extends Command {

    /**
     * Constructor that specifies user input.
     *
     * @param input User input string.
     * @throws DukeException If command is incomplete or empty.
     */
    public Todo(String input) throws DukeException {
        super("[T][ ] " + input.trim());
        if (input.matches("\\s*")) {
            throw new DukeException("todo", 1);
        }
    }

    /**
     * Executes tasks for Todo command.
     *
     * @param tasks Tasks that will be augmented.
     * @param ui Messages that will be displayed.
     * @param storage Storage to be added into.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this);
        ui.showListIncrementOutput(command,tasks.list.size());
        storage.updateListDataOnDisk(tasks.list);
    }

    /**
     * @return False, since this is not a "bye" command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
