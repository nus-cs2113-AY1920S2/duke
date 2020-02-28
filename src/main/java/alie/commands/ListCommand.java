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
        return new CommandResult(stringAllTasksTogether(allTasks));
    }

    /**
     * Converting taskList into a string for printing.
     * @param taskList
     * @return String containing all the tasks in taskList with proper spacing and line separator.
     */
    public String stringAllTasksTogether(ArrayList<Task> taskList) {
        StringBuilder allTasks = new StringBuilder();
        if (taskList.size() == 0) {
            allTasks.append("No tasks added yet");
        } else {
            allTasks.append(INDENTATION + "Here are the tasks in your list:" +
                    System.lineSeparator());
            for (Task task : taskList) {
                allTasks.append(String.format("%s %d.%s", INDENTATION,
                        convertToOneBased(taskList.indexOf(task)),
                        task.getTaskInfo() + System.lineSeparator()));
            }
        }
        return allTasks.toString();
    }
}
