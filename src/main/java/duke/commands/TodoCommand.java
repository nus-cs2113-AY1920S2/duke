package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.TaskException;


public class TodoCommand extends ExecuteCommand {
    public TodoCommand(String userData) {
        this.userData = userData;
        this.toExit = false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws TaskException {
        if (!userData.trim().toLowerCase().equals("todo")) {
            String[] removeTodo = userData.split(" ", 2);
            tasks.todoTask(removeTodo[1]);
            storage.saveData(tasks);
        } else {
            throw new TaskException("Add a task behind 'todo', task cannot be left empty.");
        }
    }
}
