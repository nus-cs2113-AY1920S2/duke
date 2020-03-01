package duke.commands;

import duke.TaskList;
import duke.Storage;
import duke.exceptions.TaskException;

public class FindCommand extends ExecuteCommand {

    public FindCommand(String userData) {
        this.userData = userData;
        this.toExit = false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws TaskException {
        if (!userData.trim().toLowerCase().equals("find")) {
            String[] removeFind = userData.split(" ", 2);
            tasks.findCommand(removeFind[1]);
            storage.saveData(tasks);
        } else {
            throw new TaskException("Add a word behind 'find' to search for relevant tasks.");
        }
    }
}