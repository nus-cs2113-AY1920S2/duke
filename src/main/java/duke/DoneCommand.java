package duke;

import java.util.ArrayList;

public class DoneCommand {
    public static void DoneCommand(ArrayList<Task> taskList, String ogString) {
        if (Parser.isDoneStatementCorrect(ogString, taskList)) {
            TaskList.markTaskAsDone(taskList, ogString);
            Ui.printStraightLine();
        }
    }
}
