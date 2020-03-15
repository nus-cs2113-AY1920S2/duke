package duke.taskList;

import duke.taskManager.Deadline;
import duke.taskManager.Events;
import duke.taskManager.Task;
import duke.taskManager.Todo;
import duke.ui.DisplayUI;

import java.util.ArrayList;

import static duke.constants.Constants.EMPTY_LINE;
import static duke.constants.Constants.LINE_DIVIDER;

public class TaskList {
    // private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Add task method for adding Todo Task into the Array of tasks
     *
     * @param line  Description of todo task
     * @param tasks List of all tasks stored
     */
    public void addTask(String line, ArrayList<Task> tasks) {
        tasks.add(new Todo(line));
    }

    /**
     * Add task method for adding Deadline or Event Tasks into the Array of tasks
     *
     * @param function      String containing either "deadline" or "event"
     * @param description_1 String Descriptions of the task(deadline/event)
     * @param description_2 Contains String date/time for deadline tasks and String location for event tasks
     * @param tasks         List of all tasks stored
     */
    public void addTask(String function, String description_1, String description_2, ArrayList<Task> tasks) {
        if (function.contains("deadline")) {
            tasks.add(new Deadline(description_1, description_2));
        } else {
            tasks.add(new Events(description_1, description_2));
        }
    }

    /**
     * Method for deleting task from the Array
     *
     * @param tasks      Array of tasks stored
     * @param taskNumber Current task number that is being deleted
     */
    public void deleteTask(ArrayList<Task> tasks, int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Calling the ui object to print the added task
     *
     * @param ui        ui object for printing statements
     * @param addedTask Current task that is being added
     * @param tasks     Array of tasks stored
     */
    public void printAddedTask(DisplayUI ui, Task addedTask, ArrayList<Task> tasks) {
        ui.showAddedTask(addedTask, tasks);
    }

    /**
     * Calling the ui object to print all tasks stored
     *
     * @param ui    ui object for printing statements
     * @param tasks Array of task stored
     */
    public void printAllTasks(DisplayUI ui, ArrayList<Task> tasks) {
        if (tasks.size() != 0) {
            ui.showAllTask();
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                ui.printToUser("    " + index + "." + tasks.get(i));
            }
            ui.printToUser(EMPTY_LINE + LINE_DIVIDER);
        } else {
            ui.showEmptyTaskList();
        }
    }
}
