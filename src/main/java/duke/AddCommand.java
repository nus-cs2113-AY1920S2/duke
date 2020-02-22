package duke;

import java.util.ArrayList;

public class AddCommand {
    public static void AddCommand(ArrayList<Task> taskList, String ogString, String taskType) {
        try {
            Ui.printStraightLine();
            TaskList.addTasks(taskList, ogString, taskType);
            Ui.printAddedStatement(taskList);
        } catch (IndexOutOfBoundsException e) {
            DukeException.markAsIncorrectFormat();
            Ui.printStraightLine();
        }
    }
}
