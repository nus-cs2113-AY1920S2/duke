package duke.commands;

import duke.parser.Parser;
import duke.tasks.Task;
import duke.tasklist.printTaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Commands {

    public ListCommand(String command, ArrayList<Task> taskList, String filePath) {
        super(command, taskList,filePath);
    }

    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isListStatementCorrect(ogString)) {
            Ui.printStraightLine();
            printTaskList.execute(taskList);
            Ui.printStraightLine();
        }
    }
}
