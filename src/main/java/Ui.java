import Tasks.Task;
import java.util.ArrayList;

/**
 * This class contains methods that handles interactions with the user.
 */
public class Ui {
    /**
     * Prints the help message.
     */
    public static void printHelpMessage() {
        printBorder();
        System.out.println("\t  _          _       \n" +
                "\t | |        | |      \n" +
                "\t | |__   ___| |_ __  \n" +
                "\t | '_ \\ / _ \\ | '_ \\ \n" +
                "\t | | | |  __/ | |_) |\n" +
                "\t |_| |_|\\___|_| .__/ \n" +
                "\t              | |    \n" +
                "\t              |_|    \n");
        printBorder();
        System.out.println("\t ADD TASK:");
        System.out.println("\t\t todo <task name>");
        System.out.println("\t\t deadline <task name> /by <date/time>");
        System.out.println("\t\t event <task name> /at <date/time>");
        printBorder();
        System.out.println("\t DISPLAY LIST OF TASKS:");
        System.out.println("\t\t list");
        printBorder();
        System.out.println("\t MARK TASK AS DONE:");
        System.out.println("\t\t done <task index>");
        printBorder();
        System.out.println("\t DELETE TASK FROM LIST:");
        System.out.println("\t\t delete <task index>");
        printBorder();
        System.out.println("\t FIND KEYWORD IN LIST:");
        System.out.println("\t\t find <keyword>");
        printBorder();
        System.out.println("\t EXIT DUKE:");
        System.out.println("\t\t bye");
        printBorder();
    }

    /**
     * Prints the message to inform user that a task has been deleted.
     * @param taskList <code>ArrayList</code> of <code>Task</code> currently.
     * @param taskIndex Index of the task to be deleted.
     */
    public static void printDeleteMessage(ArrayList<Task> taskList, int taskIndex) {
        System.out.println("\t Noted. I've removed this task: ");
        System.out.println("\t " + taskList.get(taskIndex).toString()); // Print task deleted
    }

    /**
     * Prints the message to inform user that a task has been marked as done.
     * @param taskList <code>ArrayList</code> of <code>Task</code> currently.
     * @param taskIndex Index of the task to be marked as done.
     */
    public static void printDoneMessage(ArrayList<Task> taskList, int taskIndex) {
        printBorder();
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.println("\t " + taskList.get(taskIndex).toString()); // Print task marked as done
        printBorder();
    }

    /**
     * Prints all tasks that are currently in the task list.
     * @param taskList <code>ArrayList</code> of <code>Task</code> currently.
     */
    public static void printListMessage(ArrayList<Task> taskList) {
        printBorder();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); ++i) { // Print list of tasks
            System.out.println("\t " + (i + 1) + ". " + taskList.get(i).toString());
        }
        printBorder();
    }

    /**
     * Prints the tasks which matches the user's search.
     * @param foundList <code>ArrayList</code> of <code>Task</code> that matches the user's search.
     */
    public static void printFindResults(ArrayList<Task> foundList) {
        printBorder();
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0; i < foundList.size(); ++i) { // Print list of tasks
            System.out.println("\t " + (i + 1) + ". " + foundList.get(i).toString());
        }
        printBorder();
    }

    /**
     * Prints the number of tasks in the list.
     * @param taskList <code>ArrayList</code> of <code>Task</code> currently.
     */
    public static void printListCount(ArrayList<Task> taskList) {
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list."); // Print list count
    }

    /**
     * Prints the task added to the list.
     * @param taskList <code>ArrayList</code> of <code>Task</code> currently.
     */
    public static void printTaskAdded(ArrayList<Task> taskList) {
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t " + taskList.get(taskList.size() - 1)); // Print task info
    }

    /**
     * Prints border.
     */
    public static void printBorder() {
        System.out.println("\t ____________________________________________________________");
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(" _                \n"
                + "| |               \n"
                + "| |__  _   _  ___ \n"
                + "| '_ \\| | | |/ _ \\\n"
                + "| |_) | |_| |  __/\n"
                + "|_.__/ \\__, |\\___|\n"
                + "        __/ |     \n"
                + "       |___/      \n");
    }

    /**
     * Prints the welcome message.
     */
    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the error message.
     * @param errorMessage <code>String</code> of the error message to be printed.
     */
    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

}
