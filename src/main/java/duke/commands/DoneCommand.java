package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.TaskDescriptionException;
import duke.exceptions.TaskNumberException;

public class DoneCommand extends ExecuteCommand {
    public DoneCommand(String userData) {
        this.userData = userData;
        this.toExit = false;
    }

    public void execute(TaskList tasks, Storage storage) throws TaskNumberException, TaskDescriptionException {
        if (!userData.trim().toLowerCase().equals("done")) {
            String[] newData = userData.split(" ");
            int index = Integer.parseInt(newData[1]);
            int sizeOfList = tasks.sizeOfList();
            if (index <= sizeOfList) {
                tasks.doneCommand(index);
                storage.saveData(tasks);
            } else {
                throw new TaskNumberException("Task number does not exist in the list.");
            }
        } else {
            throw new TaskDescriptionException("Add a task number after \'done\' to mark task as done.");
        }
    }
}
