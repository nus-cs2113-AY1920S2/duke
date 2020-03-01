package duke.commands;

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
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            int index = Integer.parseInt(command.replaceAll("[^\\d]", ""))-1;
            tasks.list.get(index).command = tasks.list.get(index).command.replace("[ ]", "[1]");
            ui.showDoneOutput(tasks.list.get(index).command);
            storage.updateListDataOnDisk(tasks.list);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
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
