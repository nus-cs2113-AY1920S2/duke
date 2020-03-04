package Duke;

import Duke.Tasks.Task;

import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> taskList;

    /**
     * Constructor class to create new task list if there currently isn't one saved in file.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructor class to create a task list from the tasks saved in file.
     *
     * @param taskList saved list from the previous execution of the Duke program.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public static void addTask(Task task) {
        taskList.add(task);
    }

    public static void deleteTask(Task task) {
        taskList.remove(task);
    }

    public static int getSize() {
        return taskList.size();
    }

    /**
     * Gets the actual task with that iteration in the list.
     *
     * @param i the iteration of the task in the list.
     * @return the actual task.
     */
    public static Task fetchTask(int i) {
        return taskList.get(i);
    }

    /**
     * Checks if there is only one task in the list then
     * alters displayed message when user inputs list command.
     *
     * @return "" or "s" depending on whether one or more tasks in the list respectively.
     */
    public static String checkSingular() {
        if (getSize() == 1) {
            return "";
        }
        return "s";
    }
}
