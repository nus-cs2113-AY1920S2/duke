package Commands;

import Exceptions.MissingDescriptionException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ToDoCommand extends Command{
    public ToDoCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingDescriptionException {
        String[] splitCommands = removeCommandWord(rawUserInput);
        taskList.addNewToDo(splitCommands[1]);
        storage.saveToHardDisk(taskList);
    }
}
