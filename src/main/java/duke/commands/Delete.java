package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;

public class Delete extends Command {
    public Delete(String commandDetails) {
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
            int index = Integer.parseInt(command.replaceAll("[^\\d]",""))-1;
            ui.showDeleteOutput(tasks.list.get(index).command,tasks.list.size()-1);
            tasks.removeTask(index);
            storage.updateListDataOnDisk(tasks.list);
        } catch (NumberFormatException | IndexOutOfBoundsException e){
            ui.showNonExistentTaskInList();
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
