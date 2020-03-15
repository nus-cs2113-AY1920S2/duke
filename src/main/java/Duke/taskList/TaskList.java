package duke.taskList;

import duke.storage.Storage;
import duke.taskManager.Deadline;
import duke.taskManager.Events;
import duke.taskManager.Task;
import duke.taskManager.Todo;
import duke.ui.DisplayUI;
import jdk.jfr.Event;

import java.util.ArrayList;

public class TaskList {
   // private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Add task method for adding Todo Task into the Array of tasks
     * @param line Description of todo task
     * @param tasks List of all tasks stored
     */
    public void addTask(String line, ArrayList<Task> tasks){
        tasks.add(new Todo(line));
    }

    /**
     * Add task method for adding Deadline or Event Tasks into the Array of tasks
     * @param function String containing either "deadline" or "event"
     * @param description_1 String Descriptions of the task(deadline/event)
     * @param description_2 Contains String date/time for deadline tasks and String location for event tasks
     * @param tasks List of all tasks stored
     */
    public void addTask(String function, String description_1, String description_2, ArrayList<Task> tasks){
        if(function.contains("deadline")) {
            tasks.add(new Deadline(description_1, description_2));
        } else {
            tasks.add(new Events(description_1, description_2));
        }
    }

    public void deleteTask(ArrayList<Task> tasks, int taskNumber){
        tasks.remove(taskNumber);
    }

    /**
     * Print out the added new task to user
     * @param t The last task that is added into the list
     */
    public void printAddedTask(DisplayUI ui, Task addedTask, ArrayList<Task> tasks) {
        ui.showAddedTask(addedTask, tasks);
    }

    public void printAllTasks(DisplayUI ui, ArrayList<Task> tasks) {
        if (tasks.size() != 0) {
            ui.showAllTask();
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                ui.printToUser("    " + index + "." + tasks.get(i));
            }
            ui.printToUser(DisplayUI.EMPTY_LINE + DisplayUI.LINE_DIVIDER);
        } else {
            ui.showEmptyTaskList();
        }
    }
}
