package duke.commands;

import duke.parser.Parser;
import duke.tasks.Task;
import duke.tasklist.deleteTasks;
import duke.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Commands {
    public DeleteCommand(String command, ArrayList<Task> taskList, String taskType) {
        super(command, taskList,taskType);
    }
    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isDeleteStatementCorrect(ogString, taskList)) {
            Ui.printStraightLine();
            deleteTasks.execute(taskList, ogString);
            Ui.printStraightLine();
        }
    }
}
