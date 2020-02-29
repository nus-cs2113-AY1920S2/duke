package duke;

/**
 * Text UI of the application.
 */
public class Ui {
    public static final String BORDER = "____________________________________________________________ \n";

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + BORDER + "Hello! I'm Duke!\n" + "What can I do for you?\n" + BORDER);
    }

    /**
     * Method to display a list of tasks to the user, formatted as an indexed list.
     * @param tasks list of tasks.
     */
    public static void printList(TaskList tasks) {
        System.out.println(BORDER + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++){
            System.out.println(i+1 + ". " + tasks.get(i).toString());
        }
        System.out.println(BORDER);
    }

    /**
     * Method to display a confirmation of adding a task to the list and
     * the current number of tasks in the list.
     * @param tasks list of tasks.
     * @param counter the number of tasks in the list.
     */
    public static void printAcknowledgement(TaskList tasks, int counter) {
        System.out.println(BORDER + "Got it! I've added this task: ");
        System.out.println(tasks.get(counter - 1));
        System.out.println("Now you have " + counter + " tasks in your list!\n" + BORDER);
    }

    /**
     * Method to display a confirmation of marking a task as done.
     * @param tasks list of tasks.
     * @param doneTaskNum the task number of the task that has been marked done.
     */
    public static void doneAcknowledgement(TaskList tasks, int doneTaskNum) {
        System.out.println(BORDER + "Nice! I've marked this task as done: " + tasks.get(doneTaskNum - 1).description);
        System.out.println(BORDER);
    }

    /**
     * Method to display a confirmation of deleting a task from the list and
     * the current number of tasks in the list.
     * @param tasks list of tasks.
     * @param delTaskName the name of the task that has been deleted from the list.
     */
    public static void deleteAcknowledgement(TaskList tasks, String delTaskName) {
        System.out.println(BORDER + "Noted! I've removed this task:  " + delTaskName);
        System.out.println("Now you have " + (tasks.getSize()) + " tasks in your list!\n" + BORDER);
    }

    /**
     * Method to display a list of tasks containing the user-specified keyword to the user,
     * formatted as an indexed list.
     * @param tasks list of tasks.
     * @param keyword user-specified keyword that is searched in the list.
     */
    public static void printFindList(TaskList tasks, String keyword) {
        Boolean found = false;
        System.out.println(BORDER + "Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.get(i).description.contains(keyword)) {
                System.out.println(i+1 + ". " + tasks.get(i).toString());
                found = true;
            }
        }
        if (found.equals(false)) {
            System.out.println("There are no matching task found!\n");
        }
        System.out.println(BORDER);
    }

    /**
     * Method to display a goodbye message to the user.
     */
    public static void printByeMessage() {
        System.out.println(BORDER + "Bye! Hope to see you again soon!\n" + BORDER);
    }

}
