package duke.commands;

import duke.exceptions.MissingDateTimeException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingLocationException;
import duke.exceptions.WhitespaceExceptions;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.util.ArrayList;
import java.util.Scanner;

public class DoneCommand implements Command {
    private String line;

    public DoneCommand(String restOfInput){
        this.line = restOfInput;
    }

    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks)
            throws WhitespaceExceptions {
        String l = line.replace(" ", "");
        if (l == "") {
            throw new IllegalArgumentException();
        }
        int taskNumber = Integer.parseInt(l) - 1;
        if (taskNumber >= tasks.size() || taskNumber < 0) {
            throw new NullPointerException();
        }
        tasks.get(taskNumber).markAsDone(tasks.get(taskNumber));
        String str = tasks.get(taskNumber).toString().substring(6);
        storage.appendToFile(str);
        ui.showDoneTask(tasks, taskNumber);
        return true;
    }
}
