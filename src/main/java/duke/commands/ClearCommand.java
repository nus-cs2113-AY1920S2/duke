package duke.commands;

import duke.parser.Parser;
import duke.tasklist.clearTasks;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/** Represents command that is used to call a method to clear all tasks from taskList. */
public class ClearCommand extends Commands{

    public ClearCommand(String command, ArrayList<Task> taskList, String filePath) {
        super(command, taskList,filePath);
    }

    /**
     * Calls a method to clear all tasks from a taskList.
     *
     * @param taskList List that stores the tasks mentioned until now.
     * @param ogString Original command that was fed to the user.
     */
    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isClearStatementCorrect(ogString)) {
            clearTasks.execute(taskList);
            System.out.println("TaskList is now Empty");
            Ui.printStraightLine();
        }
    }

}
