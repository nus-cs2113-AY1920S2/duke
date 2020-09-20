package alie;

import alie.task.Task;

import java.util.ArrayList;

/**
 * Object to manage the list of tasks.
 */
public class TaskManager {
    protected ArrayList<Task> taskList;

    /**
     * Default constructor to initialize new taskList.
     */
    public TaskManager () {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor to initialize taskList with pre-existing tasks.
     * @param taskList
     */
    public TaskManager (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Getter to obtain total number of tasks currently in the list.
     * @return int Number of tasks in list.
     */
    public int getNumOfTasks() {
        return taskList.size();
    }

    /**
     * Getter to obtain the entire taskList.
     * @return ArrayList containing all the tasks in the list.
     */
    public ArrayList<Task> getAllTasksAdded() {
        return taskList;
    }

    /**
     * Getter to obtain specific task based on its index in the list.
     * @param index The index of the task in the list.
     * @return The index-th item in the list.
     */
    public Task getTaskFromIndex(int index) {
        return taskList.get(index);
    }

    /**
     * Add new Task to the list
     * @param newTask Task to be added to the list
     * @return Nothing.
     */
    public void addNewTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Mark a specific task to be completed based on its index.
     * @param index The index of the task in the list.
     */
    public void markTaskCompleted(int index) {
        Task taskCompleted = taskList.get(index);
        taskCompleted.setTaskCompleted(taskCompleted);
    }

    /**
     * Remove a task from the list based on its index.
     * @param index The index of the task in the list.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }
}
