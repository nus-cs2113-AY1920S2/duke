package Commands;

import Exceptions.DukeException;
import Exceptions.EmptyListException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String rawUserInput) {
        super(rawUserInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws EmptyListException {
        taskList.printTaskList();
    }
}
