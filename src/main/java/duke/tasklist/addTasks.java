package duke.tasklist;

import duke.parser.Parser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

/**
 * Represents a class that adds a task to the taskList.
 */
public class addTasks extends TaskList {

    public addTasks(){
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
        String[] todoOrDeadlineOrEvent = Parser.returnStringToAdd(ogString, taskType);
        if (taskType.equals("event")) {
            Event t = new Event(todoOrDeadlineOrEvent[0], todoOrDeadlineOrEvent[1]);
            taskList.add(t);
        } else if (taskType.equals("deadline")) {
            Deadline t = new Deadline(todoOrDeadlineOrEvent[0], todoOrDeadlineOrEvent[1]);
            taskList.add(t);
        } else {
            Todo e = new Todo(todoOrDeadlineOrEvent[1].trim());
            taskList.add(e);
        }
    }
}
