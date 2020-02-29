package duke.ui;

import duke.task.Task;
import duke.task.TaskList;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the Duke application.
 */
public class TextUi {
    private static final String DIVIDER = "===================================================";

    /** A decorative prefix added to end of the certain sentences */
    private static final String HAPPY_FACE = "(＾▽＾)";
    private static final String SAD_FACE = "(╥_╥)";

    private Scanner sc = new Scanner(System.in);

    /**
     * Informs the user that there is missing information.
     */
    public static void printIncompleteInformation() {
        System.out.println(String.format("%50s", "Oops! Information is incomplete " + SAD_FACE));
        System.out.println(DIVIDER);
    }

    /**
     * Informs the user that the command is invalid.
     */
    public static void printInvalidCommand() {
        System.out.println(String.format("%50s", "Oops! I don't know what that means " + SAD_FACE));
        System.out.println(DIVIDER);
    }

    /**
     * Displays the to-do with relevant information entered by the user.
     * Informs the user that the to-do task is successfully added to the list.
     * Shows the total number of tasks in the list.
     */
    public static void printToDo(ArrayList<Task> tasks) {
        System.out.println(String.format("%50s", "Got it. I've added this task:"));
        System.out.println(String.format("%50s", tasks.get(tasks.size() - 1)));
        System.out.println(String.format("\n%50s", tasks.size() + " tasks in the list " + SAD_FACE));
        System.out.println(DIVIDER);
    }

    /**
     * Displays the event with relevant information entered by the user.
     * Informs the user that the event is successfully added to the list.
     * Shows the total number of tasks in the list.
     */
    public static void printEvent(ArrayList<Task> tasks) {
        System.out.println(String.format("%50s", "Got it. I've added this event:"));
        System.out.println(String.format("%50s", tasks.get(tasks.size() - 1)));
        System.out.println(String.format("\n%50s", tasks.size() + " tasks in the list " + SAD_FACE));
        System.out.println(DIVIDER);
    }

    /**
     * Displays the deadline with relevant information entered by the user.
     * Informs the user that the deadline is successfully added to the list.
     * Shows the total number of tasks in the list.
     */
    public static void printDeadline(ArrayList<Task> tasks) {
        System.out.println(String.format("%50s", "Got it. I've added this deadline:"));
        System.out.println(String.format("%50s", tasks.get(tasks.size() - 1)));
        System.out.println(String.format("\n%50s", tasks.size() + " tasks in the list " + SAD_FACE));
        System.out.println(DIVIDER);
    }

    /**
     * Shows a list of tasks related to the keyword inputted by the user, formatted as an indexed list.
     * Tasks that are not relevant to the keyword are hidden.
     */
    public static void printSearchInformation(ArrayList<Task> relatedTasks, String keyword) {
        if (relatedTasks.size() <= 0) {
            System.out.println(String.format("%50s", "Cannot find a match for the keyword: " + keyword));
            System.out.println(DIVIDER);
        } else {
            System.out.println(String.format("%50s", "Here are the matching tasks in your list:"));
            for (int i = 1; i <= relatedTasks.size(); i++) {
                System.out.println(String.format("%50s", i + ". " + relatedTasks.get(i - 1)));
            }
            System.out.println(DIVIDER);
        }
    }

    /**
     * Shows a list of tasks to the user, formatted as an indexed list.
     */
    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(String.format("%50s", "Here are the tasks in your list:"));
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(String.format("%50s", i + ". " + tasks.get(i - 1)));
        }
        System.out.println(DIVIDER);
    }

    public static void showLoadingError() {
        System.out.println(String.format("%50s", "File not found"));
        System.out.println(DIVIDER);
    }


    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void printWelcomeMessage() {
        String logo = " /$$   /$$                     /$$\n"
                + "| $$  /$$/                    |__/\n"
                + "| $$ /$$/   /$$$$$$   /$$$$$$$ /$$ /$$$$$$$\n"
                + "| $$$$$/   /$$__  $$ /$$_____/| $$| $$__  $$\n"
                + "| $$  $$  | $$$$$$$$|  $$$$$$ | $$| $$  \\ $$\n"
                + "| $$\\  $$ | $$_____/ \\____  $$| $$| $$  | $$\n"
                + "| $$ \\  $$|  $$$$$$$ /$$$$$$$/| $$| $$  | $$\n"
                + "|__/  \\__/ \\_______/|_______/ |__/|__/  |__/";

        System.out.println("Hello from\n" + logo);
        System.out.println(DIVIDER);
        String s1 = String.format("%50s", "What's your name?");
        System.out.println(s1);
    }

    /**
     * Generates and prints the goodbye message upon the exit of the application.
     */
    public static void printByeMessage(String name) {
        System.out.println(String.format("%50s", "Bye, " + name + ". Hope to see you again soon!"));
        System.out.println(DIVIDER);
    }

    public String InputUserName() {
        String name = sc.nextLine();
        printUserGreeting(name);
        return name;
    }

    /**
     * Generates and prints the user greeting upon entering name input.
     *
     * @name user input which represents name of the user
     */
    public void printUserGreeting(String name) {
        System.out.println(DIVIDER);
        System.out.println(String.format("%50s", "Hello " + name + ", Anything I can help you with?"));
        System.out.println(DIVIDER);
    }

    /**
     * Displays empty list message when there are no tasks in the list.
     */
    public static void printEmptyList() {
        System.out.println(String.format("%50s", "YAYYYY! There are no tasks in your list " + HAPPY_FACE));
        System.out.println(DIVIDER);
    }

    public static void printDoneTasks(TaskList tasks, String s) {
        try {
            int doneTask = Integer.parseInt(s)-1;
            tasks.getTask(doneTask).updateTask();
            System.out.println(String.format("%50s", "Nice! I've marked this task as done:"));
            System.out.println(String.format("%50s", tasks.getTask(doneTask)));
            System.out.println(DIVIDER);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(String.format("Task not included in the list, please try again."));
            System.out.println(DIVIDER);
        } catch (Exception e) {
            //done + empty string/invalid input
            System.out.println(String.format("%50s", SAD_FACE + " Oops! Information is incomplete."));
            System.out.println(DIVIDER);
        }
    }
}
