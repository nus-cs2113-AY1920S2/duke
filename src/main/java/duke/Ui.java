package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private static final String FOUR_SPACE_INDENT = "    ";
    private static final String SIX_SPACE_INDENT = "      ";
    private static final String BORDER = FOUR_SPACE_INDENT +
            "___________________________________________________________________";
    public static final String MAKING_DIRECTORY = "Making directory: \"data\"";
    public static final String CREATING_FILE = "Creating file: \"duke.txt\"";
    public static final String LOADING_TASKS = "Loading tasks from \"data/duke.txt\", if there are any...";
    public static final String LOADING_DONE = "Loading done.";
    public static final String EXIT_MESSAGE = "Ok, see ya!";
    public static final String THANKS_RESPONSE = "The great Taskmaster Yipyap appreciates your gratitude :-)";
    public static final String NUM_FORMAT_ERROR = "Index must be an integer, like \"1\", but not \"one\".";
    public static final String SAVE_ERROR = "An IO error was encountered while saving.";

    /**
     * Get an input from the user that is not empty (an input of only
     * whitespaces is considered empty).
     * @return User input.
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        String input = "";
        while (input.isEmpty()) {
            input = in.nextLine();
            input = input.trim();
        }
        return input; // Input is not empty
    }

    public void greet() {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Hello, I'm Taskmaster Yipyap.");
        System.out.println(FOUR_SPACE_INDENT + "I can manage your tasks, and save them automatically!");
        System.out.println(FOUR_SPACE_INDENT + "So what can I do for you, human?");
        System.out.println(BORDER);
        System.out.print("\n");
    }

    public void exit() {
        printFormattedMessage(EXIT_MESSAGE);
    }

    /**
     * Inform the user that a task has been added.
     * @param tasks The list to which the task was added.
     */
    public void printAddedTask(ArrayList<Task> tasks) {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Got it. I've added this task:");
        System.out.println(SIX_SPACE_INDENT + tasks.get(tasks.size() - 1).toString());
        System.out.println(FOUR_SPACE_INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(BORDER);
        System.out.print("\n");
    }

    /**
     * Inform the user that a task has been marked as done.
     * @param tasks The list in which the task resides.
     * @param taskIndex The index of the task being marked.
     */
    public void printDoneTask(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Nice! I've marked this task as done:");
        System.out.println(SIX_SPACE_INDENT + tasks.get(taskIndex - 1).toString());
        System.out.println(BORDER);
        System.out.print("\n");
    }

    /**
     * Inform the user that a task has been deleted.
     * @param tasks The list from which the task was deleted.
     * @param taskIndex The index of the deleted task.
     */
    public void printDeletedTask(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Noted. I've removed this task:");
        System.out.println(SIX_SPACE_INDENT + tasks.get(taskIndex - 1).toString());
        tasks.remove(taskIndex - 1);
        System.out.println(FOUR_SPACE_INDENT + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(BORDER);
        System.out.print("\n");
    }

    public void printFormattedMessage(String str) {
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + str);
        System.out.println(BORDER);
        System.out.print("\n");
    }

    public void printLine(String str) {
        System.out.println(FOUR_SPACE_INDENT + str);
    }

    /**
     * Show the list of tasks in memory.
     * @param tasks The task list.
     */
    public void printList(ArrayList<Task> tasks) {
        int bulletNum = 1;
        System.out.println(BORDER);
        System.out.println(FOUR_SPACE_INDENT + "Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(FOUR_SPACE_INDENT + bulletNum + "." + task.toString());
            bulletNum++;
        }
        System.out.println(BORDER);
        System.out.print("\n");
    }

}
