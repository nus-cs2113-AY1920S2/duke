package duke.commands;

import duke.parser.Parser;
import duke.tasks.Task;
import duke.tasklist.deleteTasks;
import duke.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Commands {

    /** Represents command that is used to call a method to delete tasks to taskList. */
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
            deleteTasks.execute(taskList, ogString);
            Ui.printStraightLine();
        }
    }
}
