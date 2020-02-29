package duke.tasklist;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    List<Task> tasks;

    /**
     * Defines the constructor and starts with an empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Defines the constructor and starts with a given ArrayList of tasks.
     * 
     * @param tasks Beginning list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds tasks into the task list.
     * 
     * @param task Task that needs to be added into the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task out of the task list.
     * 
     * @param taskNumber Task number in the task list of the unwanted task.
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    /**
     * Returns that there are how many tasks in the list.
     * 
     * @return Number of tasks in the existing list.
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Returns the wanted task via the task number.
     * 
     * @param taskNumber Number of the wanted task in the task list.
     * @return The wanted task object.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }
}
