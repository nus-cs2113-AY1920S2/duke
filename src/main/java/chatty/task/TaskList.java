package chatty.task;

import java.util.ArrayList;
import java.util.List;

/**
 * List of Tasks.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a task to the list.
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     * @param idx The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int idx) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        return task;
    }

    /**
     * Gets the total number of tasks in the list.
     * @return Total number of tasks in the list.
     */
    public int getTotalTaskNum() {
        return tasks.size();
    }

    /**
     * Gets the task at a specific index.
     * @param idx The index.
     * @return The task at the specified index.
     */
    public Task getTaskAtIdx(int idx) {
        return tasks.get(idx);
    }

    /**
     * Marks the task at a specific index as done.
     * @param idx The index.
     * @return The task marked as done.
     */
    public Task markTaskAsDone(int idx) {
        Task task = tasks.get(idx);
        task.markAsDone();
        return task;
    }
}
