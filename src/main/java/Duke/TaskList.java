package Duke;

import java.util.ArrayList;

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
     * Provides the task located in index i of the array.
     * @param i the index of the task being queried
     * @return the task located at index i
     */
    public Task get (int i) {
        return tasks.get(i);
    }


}
