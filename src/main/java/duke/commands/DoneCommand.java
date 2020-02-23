package duke.commands;

import duke.parser.Parser;
import duke.tasks.Task;
import duke.tasklist.markTaskAsDone;
import duke.ui.Ui;

import java.util.ArrayList;

public class DoneCommand extends Commands {

    public DoneCommand(String command, ArrayList<Task> taskList, String filePath) {
        super(command, taskList,filePath);
    }

    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isDoneStatementCorrect(ogString, taskList)) {
            Ui.printStraightLine();
            markTaskAsDone.execute(taskList, ogString);
            Ui.printStraightLine();
        }
    }

}
