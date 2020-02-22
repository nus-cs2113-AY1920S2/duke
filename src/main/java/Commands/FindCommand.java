package Commands;

import Exceptions.MissingDescriptionException;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String rawUserInput) {
        super(rawUserInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingDescriptionException {
        String[] splitCommands = removeCommandWord(rawUserInput);
        taskList.findTask(splitCommands[1]);
    }
}
