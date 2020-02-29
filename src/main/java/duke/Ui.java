package duke;

/**
 * Represents text that is printed to the screen to
 * for the application to interact with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________ \n";

    /**
     * Prints the welcome message to the screen.
     */
    public static void printWelcomeMessage() {
        System.out.println(LINE +
                "  Hello! I'm Duke :)\n" +
                "  What can I do for you?\n" + LINE);
    }

    /**
     * Prints a message which tells the user that a task
     * has been added successfully and tells the user how
     * many tasks he/she has in the list.
     *
     * @param numTasks number of tasks in the list
     */
    public static void printAddMessage(int numTasks) {
        System.out.println(LINE +
                "  Ok! I've added this task. \n" +
                "  Now you have " + numTasks + " tasks on your list.\n" + LINE);
    }

    /**
     * Prints a message which tells the user that a task
     * has been successfully deleted.
     *
     * @param description description of the deleted task
     * @param numTasks number of tasks left in the list
     */
    public static void printDeleteMessage(String description, int numTasks) {
        System.out.println(LINE +
                "  You have deleted: " + description + "\n" +
                "  Now you have " + numTasks + " task(s) on your list.\n" +LINE);
    }

    /**
     * Prints a message which tells the user that a task
     * has been successfully deleted.
     *
     * @param description description of the completed task
     * @param isDone checks if the task has already been marked
     *               as done
     */
     public static void printDoneMessage(String description, boolean isDone) {
        if(isDone) {
            System.out.println(LINE +
                    "  Yay! " + description + " is done!\n" + LINE);
        } else {
            System.out.println(LINE +
                    "  This task is already done!\n" + LINE);
        }

    }

    /**
     * Prints a list of all possible commands that can be entered.
     */
    public static void printHelp() {
        System.out.println( LINE +
                "  I'm sorry I do not understand :(\n\n" +
                "  I can help you with the following: \n" +
                "[list] - lists tasks \n" +
                "[todo <task_name>] - adds a todo task \n" +
                "[deadline <task_name> / <date in format: YYYY-MM-DD>] - adds a deadline task \n" +
                "[event <event_name> / <date in format: YYYY-MM-DD>] - adds an event \n" +
                "[done <task_num>] - marks a task as done \n" +
                "[date <YYYY-MM-DD>] - find deadlines or events on this date\n" +
                "[find <keyword>] - finds all tasks containing this keyword\n" +
                "[bye] - exits duke \n" + LINE);
    }

    /**
     * Prints the list of tasks with their indexes.
     * @param tasks list of tasks to be printed
     */
    public static void printList(TaskList tasks) {
        if(tasks.getSize() != 0) {
            System.out.println(LINE + "  These are the task(s) on your list: ");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.print("  " + (i + 1) + ". ");
                System.out.println(tasks.get(i));
            }
            System.out.println(LINE);
        } else {
            System.out.println(LINE +
                    "  There are no tasks in your list!\n" + LINE);
        }

    }

    /**
     * Prints an error message if user did not empty a task
     * description or date.
     */
    public static void printFieldEmpty() {
        System.out.println(LINE +
                "  Task description or date field is empty.\n" + LINE);
    }

    public static void printNoDate() {
        System.out.println(LINE +
                "  Please enter a date.\n" + LINE);
    }

    public static void printNoKeyword() {
        System.out.println(LINE +
                "  Please enter a keyword.\n" + LINE);
    }

    /**
     * Prints an error message if the user enters an invalid date.
     */
    public static void printInvalidDate() {
        System.out.println(LINE +
                "  Invalid date. Do check that it's in the format: YYYY-MM-DD\n" + LINE);
    }

    /**
     * Prints an error message if task does not exist.
     */
    public static void printDoesNotExist() {
        System.out.println(LINE +
                "  This task does not exist.\n" + LINE);
    }

    /**
     * Prints an error message if the user did not enter a number
     * for the task number.
     */
    public static void printInvalidTaskNum() {
        System.out.println(LINE +
                "  This is not a valid task number.\n" + LINE);
    }

    /**
     * Prints an error message if the task is not found.
     * @param type type of task
     */
    public static void printNotFound(String type) {
        System.out.println("  Sorry, there were no tasks with this " + type + " found. :(\n" + LINE);
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage() {
        System.out.println(LINE +
                "  Bye! See you again soon!\n" + LINE);
    }
}
