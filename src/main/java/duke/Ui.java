package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * deals with interactions with the user.
 * Handles the input from the user and also the
 * output that the user sees
 */
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
        for (int i = 0; i < TaskList.sizeOfList; i++) {
            int k = i + 1;
            System.out.println(" " + k + "." + TaskList.listOfTasks.get(i).toString());
        }
        System.out.println(Constants.STANDARD_SEPARATOR);
    }


    /**
     * Prints out the tasks with the stated keywords inside
     */
    public static void printOutFound() {
        System.out.println(Constants.STANDARD_SEPARATOR);
        if (TaskList.indexOfFound.size() == 0) { // if list is empty
            System.out.println("OOPS!! Could not find any matching tasks");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < TaskList.indexOfFound.size(); i++) {
                int index = TaskList.indexOfFound.get(i) + 1;
                System.out.println(" " + index + "." +
                        TaskList.listOfTasks.get(index - 1).toString());
            }
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
        System.out.println(" Now you have " + TaskList.sizeOfList + s3);
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
        System.out.println("   " + TaskList.listOfTasks.get(index).toString());
        System.out.println(Constants.STANDARD_SEPARATOR);
    }


    /**
     * reads the command from the user
     *
     * @return what is read from user
     */
    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    /**
     * prints the error message depending on the kind of error it is
     *
     * @param errorMsg the error Msg to print
     */
    public static void printErrMsg(String errorMsg) {
        System.out.println(Constants.STANDARD_SEPARATOR);
        System.out.println(errorMsg);
        System.out.println(Constants.STANDARD_SEPARATOR);
    }
}
