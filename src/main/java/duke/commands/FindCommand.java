package duke.commands;

import duke.parser.Parser;
import duke.tasks.Task;
import duke.tasklist.findTaskFromTaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Commands {

    public FindCommand(String command, ArrayList<Task> taskList, String taskType) {
        super(command, taskList,taskType);
    }

    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isFindStatementCorrect(ogString)) {
            Ui.printStraightLine();
            findTaskFromTaskList.execute(taskList, ogString);
            Ui.printStraightLine();
        }
    }
}
