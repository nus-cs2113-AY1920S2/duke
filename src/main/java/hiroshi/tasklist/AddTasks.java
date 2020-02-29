package hiroshi.tasklist;
import hiroshi.parser.Parser;
import hiroshi.tasks.Deadline;
import hiroshi.tasks.Event;
import hiroshi.tasks.Task;
import hiroshi.tasks.Todo;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a class that adds a task to the taskList.
 */
public class AddTasks extends TaskList {

    public AddTasks() {
        super();
    }

    /**
     * Method used to add tasks to taskList.
     *
     * @param taskList List that stores the tasks mentioned until now.
     * @param ogString Original command that was fed to the user.
     * @param taskType The type of the task to be added, eg: Todo, Event, Deadline.
     */
    public static void execute(ArrayList<Task> taskList, String ogString, String taskType) {
        String[] taskDetails = Parser.returnStringToAdd(ogString, taskType);
        if (taskType.equals("todo")) {
            String description = taskDetails[1];
            Todo e = new Todo(description.trim());
            taskList.add(e);
        } else {
            String description = taskDetails[0];
            String date = taskDetails[1];
            LocalDate dateTask = LocalDate.parse(date);
            if (taskType.equals("event")) {
                Event t = new Event(description, dateTask);
                taskList.add(t);
            } else {
                Deadline t = new Deadline(description, dateTask);
                taskList.add(t);
            }
        }
    }
}