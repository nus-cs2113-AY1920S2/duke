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
                " Hello! I'm Duke :)\n" +
                " What can I do for you?\n" + LINE);
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
                "  Now you have " + String.valueOf(numTasks) + " tasks on your list.\n " +
                LINE);
    }

    /**
     * Prints a list of all possible commands that can be entered.
     */
    public static void printHelp() {
        System.out.println("I can help you with the following: \n" +
                "[list] - lists tasks \n" +
                "[todo <task_name>] - adds a todo task \n" +
                "[deadline <task_name> / deadline] - adds a deadline task \n" +
                "[event <event_name> / event_date] - adds an event \n" +
                "[done <task_num>] - marks a task as done \n" +
                "[bye bye] - exits duke \n" + LINE);
    }

    /**
     * Prints the list of tasks with their indexes.
     * @param tasks list of tasks to be printed
     */
    public static void printList(TaskList tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(tasks.get(i));
        }
    }

    /**
     * Prints a message which tells the user that a task has
     * successfully been marked as completed.
     * @param description the description of the completed task
     */
    public static void printDone(String description) {
        System.out.println(LINE + "  Yay! You have done: " + description + "\n" + LINE);
    }
}
