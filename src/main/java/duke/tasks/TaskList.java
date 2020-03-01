package duke.tasks;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList is the public class responsible for maintaining the tasklist.
 */

public class TaskList {

    /**
     * The list of different tasks.
     */

    private List<Task> tasks;

    /**
     * Constructs the Tasklist Object.
     */

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns the tasklist.
     * @return the tasklist.
     */

    public List<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds a new task to the tasklist.
     * @param task the new task added to the tasklist.
     */

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes an existing task from the tasklist.
     * @param task the task removed from the tasklist.
     */

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Returns the length of the tasklist.
     * @return the length of the tasklist.
     */

    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Clears all tasks from the tasklist.
     */

    public void clearList() {
        this.tasks.clear();
    }

}
