package hiroshi.commands;

import hiroshi.parser.Parser;
import hiroshi.tasks.Task;
import hiroshi.tasklist.DeleteTasks;
import hiroshi.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Commands {

    /** Represents command that is used to call a method to delete tasks to taskList.
     *
     * @param command Command that user inputs.
     * @param taskList Tasklist of all available tasks.
     * @param taskType Type of task, eg event.
     */
    public DeleteCommand(String command, ArrayList<Task> taskList, String taskType) {
        super(command, taskList,taskType);
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
            DeleteTasks.execute(taskList, ogString);
            Ui.printStraightLine();
        }
    }
}