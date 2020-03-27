package hiroshi.commands;

import hiroshi.parser.Parser;
import hiroshi.tasks.Task;
import hiroshi.tasklist.FindTaskFromTaskList;

import java.util.ArrayList;

/** Represents command that is used to find a method in a taskList. */
public class FindCommand extends Commands {

    public FindCommand(String command, ArrayList<Task> taskList, String taskType) {
        super(command, taskList,taskType);
    }

    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isFindStatementCorrect(ogString)) {
            FindTaskFromTaskList.execute(taskList, ogString);
        }
    }
}