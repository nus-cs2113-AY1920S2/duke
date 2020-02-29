package hiroshi.commands;

import hiroshi.parser.Parser;
import hiroshi.tasks.Task;
import hiroshi.tasklist.PrintTaskList;
import hiroshi.ui.Ui;

import java.util.ArrayList;

/** Represents command that is used to call a method to print tasks from the taskList. */
public class ListCommand extends Commands {

    public ListCommand(String command, ArrayList<Task> taskList, String filePath) {
        super(command, taskList,filePath);
    }

    /**
     * Calls a method to print tasks from the taskList.
     *
     * @param taskList List that stores the tasks mentioned until now.
     * @param ogString Original command that was fed to the user.
     */
    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isListStatementCorrect(ogString)) {
            PrintTaskList.execute(taskList);
            Ui.printStraightLine();
        }
    }
}