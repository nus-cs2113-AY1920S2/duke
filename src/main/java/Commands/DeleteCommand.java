package Commands;

import Exceptions.DukeException;
import Exceptions.MissingDescriptionException;
import Exceptions.MissingItemIndexException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String rawUserInput) {
        super(rawUserInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingItemIndexException {
        try {
            String[] splitCommands = removeCommandWord(rawUserInput);
            int itemIndex = Integer.parseInt(splitCommands[1]) - 1;
            taskList.deleteTask(itemIndex);
            storage.saveToHardDisk(taskList);
        } catch (MissingDescriptionException e) {
            throw new MissingItemIndexException(rawUserInput);
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer to be marked done");
        }
    }
}

