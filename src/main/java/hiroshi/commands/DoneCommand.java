package hiroshi.commands;

import hiroshi.parser.Parser;
import hiroshi.tasks.Task;
import hiroshi.tasklist.markTaskAsDone;
import hiroshi.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a class that calls a method to mark a given task as done.
 */
public class DoneCommand extends Commands {

    public DoneCommand(String command, ArrayList<Task> taskList, String filePath) {
        super(command, taskList,filePath);
    }

    /**
     * Calls a method to add tasks to check if the format of the done command is correct and marks a given task as done.
     *
     * @param taskList List that stores the tasks mentioned until now.
     * @param ogString Original command that was fed to the user.
     */
    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isDoneStatementCorrect(ogString, taskList)) {
            Ui.printStraightLine();
            markTaskAsDone.execute(taskList, ogString);
            Ui.printStraightLine();
        }
    }

}
