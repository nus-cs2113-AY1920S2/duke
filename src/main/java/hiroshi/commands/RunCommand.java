package hiroshi.commands;

import hiroshi.common.Utils;
import hiroshi.parser.Parser;
import hiroshi.tasks.Task;
import hiroshi.ui.Ui;

import java.util.ArrayList;

/** Represents a subclass of Command, RunCommand, that can execute the program based on the command of the user */
public class RunCommand extends Commands {

    public RunCommand(String command, ArrayList<Task> taskList, String filePath) {
        super(command, taskList,filePath);
    }

    /**
     * Reads what type of task if given and calls the required method accordingly.
     *
     * @param command  Line that represents the task that is supposed to marked as done.
     * @param taskList Tasklist of  all available tasks.
     */
    @Override
    public void execute(String command, ArrayList<Task> taskList, String filePath) {
        String taskType = Parser.returnTaskType(finalCommand);
        if (!Utils.checkIfValidTask(taskType)) {
            Ui.markAsIncorrectFormat();
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
            DoneCommand.execute(taskList, ogString);
            break;
        case "list":
            ListCommand.execute(taskList, ogString);
            break;
        case "delete":
            DeleteCommand.execute(taskList, ogString);
            break;
        case "clear":
            ClearCommand.execute(taskList, ogString);
            break;
        case "find":
            FindCommand.execute(taskList, ogString);
            break;
        case "deadline":
        case "event":
        case "todo":
            AddCommand.execute(taskList, ogString, taskType);
        }
    }
}