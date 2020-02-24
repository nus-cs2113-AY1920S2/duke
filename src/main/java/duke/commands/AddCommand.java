package duke.commands;

import duke.exceptions.DukeException;
import duke.tasklist.addTasks;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class AddCommand extends Commands {
    public AddCommand(String command, ArrayList<Task> taskList, String filePath) {
        super(command, taskList,filePath);
    }

    public static void execute(ArrayList<Task> taskList, String ogString, String taskType) {
        try {
            Ui.printStraightLine();
            try {
                addTasks.execute(taskList, ogString, taskType);
                Ui.printAddedStatement(taskList);
            } catch (java.time.format.DateTimeParseException ex) {
                Ui.markDateTimeException();
                Ui.printStraightLine();
            }

        } catch (IndexOutOfBoundsException e) {
            Ui.markAsIncorrectFormat();
            Ui.printStraightLine();
        }
    }
}
