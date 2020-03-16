package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The TaskList class is the class involved in getting and setting Task Object into an ArrayList.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Empty constructor for TaskList. Used when there are no existing Tasks stored yet.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Public constructor for TaskList.
     * @param tasks ArrayList of Tasks to be stored.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            this.tasks.add(tasks.get(i));
        }
        //this.tasks = tasks;
    }

    /**
     * Takes in a Task Object and add it into the stored task list.
     * @param task The Task Object to be stored.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Return the Task Object stored at a specific task index.
     * @param taskIndex The index of the Task Object in the ArrayList.
     * @return Task Object stored at the index provided.
     */
    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Provides the count of task stored.
     * @return Total number of task stored.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Delete the Task stored at a specific index.
     * @param deleteIndex The index of the task to be deleted.
     */
    public void deleteTask(int deleteIndex) {
        tasks.remove(deleteIndex);
    }

    /**
     * Mark a Task as completed.
     * @param doneIndex The index of the task that is done.
     */
    public void doneTask(int doneIndex) {
        tasks.get(doneIndex).markAsDone();
    }
}
