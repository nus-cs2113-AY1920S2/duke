package Commands;

import Exceptions.MissingDateException;
import Exceptions.MissingDescriptionException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class EventCommand extends Command {
    public EventCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws MissingDescriptionException, MissingDateException {
        String[] splitCommands = removeCommandWord(rawUserInput);
        String[] eventSplit = splitDate(splitCommands[1]);
        taskList.addNewEvent(eventSplit[0].trim(), eventSplit[1].trim());
        storage.saveToHardDisk(taskList);
    }
}
