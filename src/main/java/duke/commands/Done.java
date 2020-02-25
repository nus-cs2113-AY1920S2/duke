package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;

public class Done extends Command {
    public Done(String commandDetails) {
        super(commandDetails);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            int index = Integer.parseInt(command.replaceAll("[^\\d]", ""))-1;
            tasks.list.get(index).command = tasks.list.get(index).command.replace("✗", "✓");
            ui.showDoneOutput(tasks.list.get(index).command);
            storage.updateListDataOnDisk(tasks.list);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.showNonExistentTaskInList();
        }
    }


    @Override
    public boolean isExit(){
        return false;
    }
}
