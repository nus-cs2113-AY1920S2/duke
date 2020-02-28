package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
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
     * Gets task at index i.
     *
     * @param i index to get the task at
     * @return the task at i
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Gets the number of tasks in the list of tasks.
     *
     * @return number of tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Removes the task at int i.
     *
     * @param i index to remove the task at
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Adds a new task to the back of the list.
     *
     * @param task task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }
}
