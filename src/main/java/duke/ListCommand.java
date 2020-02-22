package duke;

import java.util.ArrayList;

public class ListCommand {
    public static void ListCommand(ArrayList<Task> taskList, String ogString) {
        if (Parser.isListStatementCorrect(ogString)) {
            Ui.printStraightLine();
            TaskList.printTaskList(taskList);
            Ui.printStraightLine();
        }
    }
}
