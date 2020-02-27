package duke;

import duke.task.Task;
import java.util.Scanner;

public class Ui {

    /**
     * Prints the exit message
     */
    public static void printExitMessage() {
        System.out.println(Constants.STANDARD_SEPARATOR);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(Constants.STANDARD_SEPARATOR);
    }


    /**
     * Prints out the entire list
     */
    public static void printsOutTheList() {
        System.out.println(Constants.STANDARD_SEPARATOR);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < Duke.sizeOfList; i++) {
            int k = i + 1;
            System.out.println(" " + k + "." + Duke.listOfTasks.get(i).toString());
        }
        System.out.println(Constants.STANDARD_SEPARATOR);
    }


    /**
     * Prints out the welcome message
     */
    public static void printWelcomeMessage() {
        System.out.println("Hello from " + Constants.SHANNON);

        System.out.println(Constants.STANDARD_SEPARATOR);
        System.out.println(" Hello! I'm " + Constants.SHANNON
                + System.lineSeparator() + " What can I do for you?");
        System.out.println(Constants.STANDARD_SEPARATOR);
    }


    /**
     * Prints the required when encounters tasks are
     * added or removed to inform user command carried out
     *
     * @param currTask task that is added or removed
     * @param s        either "removed" or "added",
     *                 depending on command
     * @param s2       string of double spacing
     * @param s3       "task in the list"
     */
    public static void printWhenCommand(Task currTask, String s, String s2, String s3) {
        System.out.println(Constants.STANDARD_SEPARATOR);
        System.out.println(s);
        System.out.println(s2 + currTask.toString());
        System.out.println(" Now you have " + Duke.sizeOfList + s3);
        System.out.println(Constants.STANDARD_SEPARATOR);
    }


    /**
     * Prints out the required to inform user the task that has been marked
     *
     * @param index index of the task marked
     */
    public static void printWhenMarked(int index) {
        System.out.println(Constants.STANDARD_SEPARATOR);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + Duke.listOfTasks.get(index).toString());
        System.out.println(Constants.STANDARD_SEPARATOR);
    }


    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void printErrMsg(String errMissingParam) {
        System.out.println(Constants.STANDARD_SEPARATOR);
        System.out.println(errMissingParam);
        System.out.println(Constants.STANDARD_SEPARATOR);
    }
}
