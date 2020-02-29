package duke;

import types.Task;

public class TaskList {

    /**
     * List of all tasks added by the user
     */
    private static types.Task[] taskList;

    /**
     * Maximum number of tasks
     */
    private static final int MAX_TASKS = 100;

    /**
     * Number of tasks in taskList
     */
    private static int numTasks;

    /**
     * A list of all tasks
     */
    public TaskList() {
        taskList = new types.Task[MAX_TASKS];
        numTasks = 0;
    }

    /**
     * Add task to list and increment number of tasks
     * @param t task to be added to list
     */
    static void addTask(types.Task t) {
        taskList[numTasks] = t;
        numTasks++;
    }

    /**
     * Removes an item from the task array
     * @param arr original array
     * @param index of item to be deleted
     * @return the new array
     */
     static types.Task[] deleteIndex(types.Task[] arr, int index) {
        types.Task[] result = new types.Task[arr.length - 1];
        for (int i = 0; i < result.length; i++) {
            if (i < index) {
                result[i] = arr[i];
            } else {
                result[i] = arr[i+1];
            }
        }
        numTasks--;
        return result;
    }

    /**
     * Set number of tasks
     * @param i number of tasks
     */
    public static void setNumTasks(int i) {
        TaskList.numTasks = i;
    }

    /**
     * Get task list
     * @return task list
     */
    public static Task[] getTaskList() {
        return taskList;
    }

    /**
     * Get number of tasks
     * @return number of tasks
     */
    public static int getNumTasks() {
        return numTasks;
    }

    /**
     * Set task list
     * @param taskList list to set to
     */
    public static void setTaskList(Task[] taskList) {
        TaskList.taskList = taskList;
    }

}
