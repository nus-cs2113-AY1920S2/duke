package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import static duke.utils.Constants.LIST_COMMAND;

public class ListCommand extends Command {
    public ListCommand() {
        this.command = LIST_COMMAND;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        ui.listTasks(tasks);
    }
}
