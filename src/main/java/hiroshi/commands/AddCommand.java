package hiroshi.commands;

import hiroshi.tasklist.AddTasks;
import hiroshi.tasks.Task;
import hiroshi.ui.Ui;

import java.util.ArrayList;

/** Represents command that is used to call a method to add tasks to taskList. */
public class AddCommand extends Commands {

    public AddCommand(String command, ArrayList<Task> taskList, String filePath) {
        super(command, taskList,filePath);
    }

    /**
     * Calls a method to add tasks to taskList and prints the revalent message about the task that has been added.
     *
     * @param taskList List that stores the tasks mentioned until now.
     * @param ogString Original command that was fed to the user.
     * @param taskType The type of the task to be added, eg: Todo, Event, Deadline.
     */
    public static void execute(ArrayList<Task> taskList, String ogString, String taskType) {
        try {
            try {
                AddTasks.execute(taskList, ogString, taskType);
                Ui.printAddedStatement(taskList);
            } catch (java.time.format.DateTimeParseException ex) {
                Ui.markDateTimeException();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.markAsIncorrectFormat();
        }
    }
}