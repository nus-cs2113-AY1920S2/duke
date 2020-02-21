package duke.task;

import java.util.ArrayList;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    /** The underlying data structure to store the tasks */
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task requested.
     * @return Task at the specified index.
     */
    public static Task getIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public static int getSize() {
        return tasks.size();
    }

    /**
     * Clears the list.
     */
    public static void clearList() {
        tasks.clear();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index Index of the task to be marked as done.
     */
    public static void markTask(int index) {
        Task completedTask = tasks.get(index);
        completedTask.markAsDone();
    }

    /**
     * Adds a new task to the list.
     *
     * @param newItem Task to be added.
     */
    public static void add(Task newItem) {
        tasks.add(newItem);
    }

    /**
     * Removes a task at the specified index.
     *
     * @param index Index of the task to be removed.
     */
    public static void removeTask(int index) {
        Task unwantedTask = tasks.get(index);
        tasks.remove(unwantedTask);
    }
}
