package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.task.Task;

import java.util.ArrayList;

/**
 * Command to print all the tasks added to task list
 */
public class ListCommand extends Command {
    public static final String COMMAND_KEYWORD = "list";

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) {
        ArrayList<Task> allTasks = taskLists.getAllTasksAdded();
        return new CommandResult(stringAllTasksInList(allTasks, allTasks));
    }
}
