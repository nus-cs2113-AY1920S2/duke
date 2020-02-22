package duke;

import java.util.ArrayList;

public class RunCommand {
    /**
     * Reads what type of task if given and calls the required method accordingly.
     *
     * @param command    Line that represents the task that is supposed to marked as done.
     * @param taskList Tasklist of  all available tasks.
     */
    public void runCommand(String command, ArrayList<Task> taskList, String filePath) {
        String taskType = returnTaskType(finalCommand);
        if (!checkIfValidTask(taskType)) {
            DukeException.markAsIncorrectFormat();
        } else {
            executeCommands(taskList, command, taskType);
        }

    }

    /**
     * Calls different methods to do the required tasks
     *
     * @param taskList TaskList of all available tasks.
     * @param ogString Original string that is inputted by the user using the command line.
     * @param taskType event Type, meaning the nature of the command (eg. LIST, DEADLINE).
     */
    public static void executeCommands(ArrayList<Task> taskList, String ogString, String taskType) {
        switch (taskType) {
        case "done":
            DoneCommand.DoneCommand(taskList, ogString);
            break;
        case "list":
            ListCommand.ListCommand(taskList, ogString);
            break;
        case "delete":
            DeleteCommand.DeleteCommand(taskList, ogString);
            break;
        case "deadline":
        case "event":
        case "todo":
            AddCommand.AddCommand(taskList, ogString, taskType);
        }
    }
}
