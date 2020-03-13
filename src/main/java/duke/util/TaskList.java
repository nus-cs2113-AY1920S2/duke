package duke.util;

import duke.taskmanager.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * Task list where active tasks are stored.
     */
    public static List<Task> tasks = new ArrayList<>();
    public static String FORMAT = Split.FORMAT.getSplit();
    public static String SPLIT_UPPER_BOUNDARY = Split.SPLIT_UPPER_BOUNDARY.getSplit();
    public static String SPLIT_LOWER_BOUNDARY = Split.SPLIT_LOWER_BOUNDARY.getSplit();

    public TaskList() {
        tasks = new ArrayList<>();
        Load load = new Load();
        tasks = load.readData();
    }


    public static void printIntro() {
        System.out.printf(FORMAT, "Your current task list:");
    }

    public static void printEmpty() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "You have no ongoing task.");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }

    /**
     * Return the task list retrieved from file.
     * @return tasks, the task list retrieved from file
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Return the Tasks at index.
     * @param index an integer representing the index of task selected
     * @return      the Tasks at tasks[index]
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Add a Tasks to the current task list, List<Tasks>.
     * @param task the Tasks to be added to tasks
     */
    public static void add(Task task) {
        tasks.add(task);
    }

    /**
     * Return the size of current task list, List<Tasks>.
     * @return an integer stating the size of tasks
     */
    public static int getSize() {
        return tasks.size();
    }

    /**
     * Print tasks in a particular format on the screen.
     * Check whether the task list is empty, and print different
     * message if the list is empty.
     * If it is not empty, iterate through the current task list
     * and print the tasks on the screen.
     */
    public static void showList() {
        printIntro();
        if (getSize() == 0){
            printEmpty();
        } else {
            int index = 0;
            for (Task task : tasks) {
                System.out.printf(FORMAT, index + ". "+ task.toString());
                index++;
            }
            System.out.println(SPLIT_LOWER_BOUNDARY);
        }
    }
}
