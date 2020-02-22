package Commands;

import Exceptions.MissingDescriptionException;
import Exceptions.MissingItemIndexException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class DoneCommand extends Command {
    public DoneCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingItemIndexException {
        try {
            String[] splitCommands = removeCommandWord(rawUserInput);
            int itemIndex = Integer.parseInt(splitCommands[1]) - 1;
            taskList.markTaskAsDone(itemIndex);
            storage.saveToHardDisk(taskList);
        } catch (MissingDescriptionException e) {
            throw new MissingItemIndexException(rawUserInput);
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer to be marked done");
        }
    }
}
