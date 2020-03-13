package duke.commands;

import duke.taskmanager.Task;
import duke.util.Split;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {
    public static List<Task> tasks = new ArrayList<>();
    public static String FORMAT = Split.FORMAT.getSplit();
    public static String SPLIT_UPPER_BOUNDARY = Split.SPLIT_UPPER_BOUNDARY.getSplit();
    public static String SPLIT_LOWER_BOUNDARY = Split.SPLIT_LOWER_BOUNDARY.getSplit();

    public ListCommand() {
    }

    public static void printIntro() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "Your current task list:");
    }

    /**
     * Message print when the current task list is empty.
     */
    public static void printEmpty() {
        System.out.println(SPLIT_UPPER_BOUNDARY);
        System.out.printf(FORMAT, "You have no ongoing task.");
        System.out.println(SPLIT_LOWER_BOUNDARY);
    }

    /**
     * Print the current task list. Check whether the
     * task list is empty, and print different message if
     * the list is empty. If it is not empty, iterate through
     * the current task list and print it on the screen.
     * @param list the current task list
     */
    public static void execute(List<Task> list) {
        printIntro();
        if (list.size() == 0){
            printEmpty();
        } else {
            int index = 0;
            for (Task task : list) {
                System.out.printf(FORMAT, index + ". "+ task.toString());
                index++;
            }
            System.out.println(SPLIT_LOWER_BOUNDARY);
        }
    }
}
