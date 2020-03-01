package Duke;

import java.util.ArrayList;

/**
 * Represents the list of tasks (todos, events and deadlines) relevant to the current user session.
 * Provides methods to add tasks, delete tasks and retrieve tasks in the list.
 */
public class TaskList {
    ArrayList<Task> tasks;
    int size;

    /**
     * Constructor for a new task array for the session.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        size = 0;
    }

    /**
     * Adds a new task to the task array.
     * @param task the task object to be added to the array
     */
    public void addTask (Task task) {
        tasks.add(task);
        size++;
    }

    /**
     * Deletes a task from the task array.
     * @param task the task object to be deleted from the array
     */
    public void deleteTask (Task task) {
        tasks.remove(task);
        size--;
    }

    /**
     * Provides the task located in index index of the array.
     * @param index the index of the task being queried
     * @return the task located at index index
     */
    public Task get (int index) {
        return tasks.get(index);
    }

    /**
     * Checks if a task exists in the list.
     * @param task the task to check
     * @return true if tasks exists in the list, false if it doesn't
     */
    public boolean taskExists (Task task) {
        if (tasks.contains(task) == true) {
            return true;
        } else {
            return false;
        }
    }
}
