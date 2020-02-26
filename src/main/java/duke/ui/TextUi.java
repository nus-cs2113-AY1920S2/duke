package duke.ui;

import duke.common.Messages;
import duke.data.task.Task;
import duke.data.task.TaskList;

import java.util.Scanner;

import static duke.common.Messages.*;

/**
 * Text UI of the application.
 */
public class TextUi {

    /**
     * Read from user input.
     */
    private Scanner in = new Scanner(System.in);

    /**
     * Print message to show user that the task list is empty.
     */
    public static void printEmptyList() {
        System.out.println(Messages.MESSAGE_EMPTY_LIST);
    }

    /**
     * Shows a list of tasks to the user, formatted as an indexed list.
     */
    public static void printList(TaskList taskList) {
        System.out.println(MESSAGE_TASKS_LIST);
        int taskCounter = taskList.size();
        for (int i = 0; i < taskCounter; i++) {
            System.out.println("    " + (i + 1) + "." + taskList.get(i));
        }
    }

    /**
     * Prints the exit message upon exiting the application.
     */
    public static void printExit() {
        System.out.println(Messages.MESSAGE_GOODBYE);
    }

    /**
     * Prints the done task upon completion.
     * @param tasklist Current list of tasks.
     * @param index Index to indicate the done task.
     */
    public static void printDone(TaskList tasklist, int index) {
        System.out.println(Messages.MESSAGE_TASK_DONE);
        System.out.println(Messages.SPACER + tasklist.get(index));
    }

    /**
     * Print the deleted task upon completion.
     * @param tasklist Current list of tasks.
     * @param index Index to indicate the done task.
     */
    public static void printDelete(TaskList tasklist, int index) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + tasklist.get(index));
    }

    /**
     * Prints the reminding tasks.
     * @param taskCounter index to indicate the reminding task.
     */
    public static void printRemaining(int taskCounter) {
        System.out.println("    Now you have " + taskCounter + " task(s) in the list.");
    }

    /**
     * Prints the new task that is added in the task list.
     * @param task New task that is currently added.
     * @param taskCounter Index to indicate the total number of tasks.
     */
    public static void printTask(Task task, int taskCounter) {
        System.out.println("    Got it! I've added this task:");
        System.out.println("       " + task);
        taskCounter++;
        printRemaining(taskCounter);
    }

    /**
     * Prints to alert the user that keyword is not found in the task list.
     */
    public static void printKeyNotFound(String description) {
        System.out.println("    " + description + MESSAGE_KEY_NOT_FOUND);
    }

    /**
     * Prints to show the user that keyword is found in the task list.
     * Generate a list of tasks to show the user which task are involved.
     * @param keyword The word used for search
     * @param tasklist Current list of tasks.
     */
    public static void printTaskFound(String keyword, TaskList tasklist) {
        int counter = 1;
        System.out.println(Messages.MESSAGE_TASK_FOUND);
        for (Task task : tasklist) {
            if (task.getDescription().contains(keyword)) {
                System.out.println("       " + counter + "." + task);
                counter++;
            }
        }
    }

    /**
     * Prints the welcome message upon exiting the application.
     */
    public void showWelcome() {
        String logo = MESSAGE_WELCOME;
        System.out.println(DIVIDER);
        System.out.print(logo);
        System.out.println(DIVIDER);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Read the next input line from user.
     */
    public String readCommand() {
        return in.nextLine();
    }
}
