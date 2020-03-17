package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class Done extends Command {
    public Done(String commandDetails) {
        super(commandDetails);
    }

    /**
     * @param tasks     the tasks that will be augmented
     * @param ui        the messages that will be displayed
     * @param storage   the storage to be added into
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(command.replaceAll("[^\\d]", "")) - 1;
            if (tasks.list.get(index).command.contains("[1]")) {
                throw new DukeException("done", 3);
            }
            if (command.matches(".*-(\\d).*")) {
                throw new DukeException("negative", 4);
            }
            tasks.list.get(index).command = tasks.list.get(index).command.replace("[ ]", "[1]");
            ui.showDoneOutput(tasks.list.get(index).command);
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
     * @return false, since this is not a "bye" command.
     */
    @Override
    public boolean isExit(){
        return false;
    }
}
