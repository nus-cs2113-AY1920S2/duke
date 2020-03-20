package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Extension of <code>Command</code> class specifying the <code>Delete</code> command.
 */
public class Delete extends Command {
    public Delete(String commandDetails) {
        super(commandDetails);
    }

    /**
     * Executes tasks for Delete command.
     *
     * @param tasks Tasks that will be augmented.
     * @param ui Messages that will be displayed.
     * @param storage Storage to be added into.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (command.matches(".*-(\\d).*")) {
                throw new DukeException("negative", 4);
            }
            int index = Integer.parseInt(command.replaceAll("[^\\d]","")) - 1;
            ui.showDeleteOutput(tasks.list.get(index).command,tasks.list.size() - 1);
            tasks.removeTask(index);
            storage.updateListDataOnDisk(tasks.list);
        } catch (IndexOutOfBoundsException e) {
            ui.showDoneOutOfBound(tasks.list.size());
        } catch (NumberFormatException e) {
            ui.showErrorInput();
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    /**
     * @return False, since this is not a "bye" command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
