package hiroshi.commands;

import hiroshi.parser.Parser;
import hiroshi.tasks.Task;
import hiroshi.tasklist.deleteTasks;
import hiroshi.ui.Ui;

import java.util.ArrayList;

/** Represents command that is used to call a method to delete tasks to taskList. */
public class DeleteCommand extends Commands {

    public DeleteCommand(String command, ArrayList<Task> taskList, String filePath) {
        super(command, taskList,filePath);
    }

    /**
     * Calls a method to delete a task from the taskList.
     *
     * @param taskList List that stores the tasks mentioned until now.
     * @param ogString Original command that was fed to the user.
     */
    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isDeleteStatementCorrect(ogString, taskList)) {
            Ui.printStraightLine();
            deleteTasks.execute(taskList, ogString);
            Ui.printStraightLine();
        }
    }

}
