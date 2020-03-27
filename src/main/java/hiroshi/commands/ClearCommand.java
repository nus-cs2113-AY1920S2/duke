package hiroshi.commands;

import hiroshi.parser.Parser;
import hiroshi.tasklist.ClearTasks;
import hiroshi.tasks.Task;
import hiroshi.ui.Ui;

import java.util.ArrayList;

/** Represents command that is used to call a method to clear all tasks from taskList. */
public class ClearCommand extends Commands{

    public ClearCommand(String command, ArrayList<Task> taskList, String taskType) {
        super(command, taskList,taskType);
    }

    /**
     * Calls a method to clear all tasks from a taskList.
     *
     * @param taskList List that stores the tasks mentioned until now.
     * @param ogString Original command that was fed to the user.
     */
    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isClearStatementCorrect(ogString)) {
            ClearTasks.execute(taskList);
            System.out.println("TaskList is now Empty");
            Ui.printStraightLine();
        }
    }
}