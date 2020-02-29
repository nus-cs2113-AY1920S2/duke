package duke.util;

import duke.exceptions.IllegalDeleteException;
import duke.taskmanager.Tasks;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * Task list where active tasks are stored.
     */
    public static List<Tasks> tasks = new ArrayList<>();
    private static UI ui;

    public TaskList(UI ui) throws IOException {
        TaskList.ui = ui;
        tasks = new ArrayList<>();
        Load load = new Load(Paths.get("data/myTasks.txt"));
        tasks = load.readData();
    }


    public static void printIntro() {
        System.out.printf(ui.FORMAT, "Your current task list:");
    }

    public static void printEmpty() {
        System.out.println(ui.SPLIT_UPPER_BOUNDARY);
        System.out.printf(ui.FORMAT, "You have no ongoing task.");
        System.out.println(ui.SPLIT_LOWER_BOUNDARY);
    }

    /**
     * Return the task list retrieved from file.
     * @return tasks, the task list retrieved from file
     */
    public List<Tasks> getTasks() {
        return tasks;
    }

    /**
     * Return the Tasks at index.
     * @param index an integer representing the index of task selected
     * @return      the Tasks at tasks[index]
     */
    public static Tasks getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Add a Tasks to the current task list, List<Tasks>.
     * @param task the Tasks to be added to tasks
     */
    public static void add(Tasks task) {
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
            for (Tasks task : tasks) {
                System.out.printf(ui.FORMAT, index + ". "+ task.toString());
                index++;
            }
            System.out.println(ui.SPLIT_LOWER_BOUNDARY);
        }
    }
}
