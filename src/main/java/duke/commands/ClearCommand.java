package duke.commands;

import duke.parser.Parser;
import duke.tasklist.clearTasks;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class ClearCommand extends Commands{
    public ClearCommand(String command, ArrayList<Task> taskList, String taskType) {
        super(command, taskList,taskType);
    }
    public static void execute(ArrayList<Task> taskList, String ogString) {
        if (Parser.isClearStatementCorrect(ogString)) {
            clearTasks.execute(taskList);
            System.out.println("TaskList is now Empty");
            Ui.printStraightLine();
        }
    }
}
