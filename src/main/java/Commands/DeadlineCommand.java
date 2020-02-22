package Commands;

import Exceptions.MissingDateException;
import Exceptions.MissingDescriptionException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws IndexOutOfBoundsException, MissingDescriptionException, MissingDateException {
        String[] splitCommands = removeCommandWord(rawUserInput);
        String[] deadlineSplit = splitDate(splitCommands[1]);
        taskList.addNewDeadline(deadlineSplit[0].trim(), deadlineSplit[1].trim());
        storage.saveToHardDisk(taskList);
    }
}
