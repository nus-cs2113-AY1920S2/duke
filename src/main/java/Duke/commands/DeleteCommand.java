package duke.commands;

import duke.exceptions.WhitespaceExceptions;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;

public class DeleteCommand implements Command {
    private String line;

    public DeleteCommand(String restOfInput){
        this.line = restOfInput;
    }

    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks)
            throws WhitespaceExceptions {
        int taskNumber;
        String str;
        if (line == "") {
            throw new IllegalArgumentException();
        }
        taskNumber = Integer.parseInt(line.replaceAll(" ","")) - 1;
        if (taskNumber >= tasks.size() || taskNumber < 0) {
            throw new NullPointerException();
        }
        ui.showDeletedTasks(tasks, taskNumber);
        str = tasks.get(taskNumber).toString().substring(6);
        storage.deleteToFile(str);
        taskList.deleteTask(tasks, taskNumber);

        return true;
    }
}
