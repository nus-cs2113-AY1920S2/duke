package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Represents the list of tasks in the application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor to create a new list of tasks
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Method to get task at the specified index.
     *
     * @param i the index of the task to get.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Method to get the number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Method to remove the specified task from the task list.
     *
     * @param i the index of the task to be removed.
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Method to add a new task to the task list.
     *
     * @param task the task to be added into the task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

}
