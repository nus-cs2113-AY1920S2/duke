package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Extension of <code>Command</code> class specifying the <code>Deadline</code> command.
 */
public class Deadline extends Command {

    /**
     * Constructor that specifies user input.
     *
     * @param input User input string.
     * @throws DukeException If command is incomplete or empty.
     */
    public Deadline(String input) throws DukeException {
        super("[D][ ] "
                + input.replaceFirst("\\s*/by\\s*"," (by: ").trim() + ")");
        if (input.replaceFirst("/by(.*)","").matches("\\s*")) {
            throw new DukeException("deadline",1);
        }
        if (!input.matches(".*/by\\s+\\w+.*")) {
            throw new DukeException("deadline", 2);
        }
    }

    /**
     * Executes tasks for Deadline command.
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
