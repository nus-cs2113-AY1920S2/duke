package duke;

import java.util.ArrayList;

public class DeleteCommand {
    public static void DeleteCommand(ArrayList<Task> taskList, String ogString) {
        if (Parser.isDeleteStatementCorrect(ogString, taskList)) {
            TaskList.deleteTask(taskList, ogString);
            Ui.printStraightLine();
        }
    }
}
