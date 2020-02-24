package duke;

/**
 * Represents text that is printed to the screen to
 * for the application to interact with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________ \n";

    public static void printWelcomeMessage() {
        System.out.println(LINE +
                " Hello! I'm Duke :)\n" +
                " What can I do for you?\n" + LINE);
    }


    public static void printAddMessage(int numTasks) {
        System.out.println(LINE +
                "  Ok! I've added this task. \n" +
                "  Now you have " + String.valueOf(numTasks) + " tasks on your list.\n " +
                LINE);
    }

    public static void printHelp() {
        System.out.println("I can help you with the following: \n" +
                "[list] - lists tasks \n" +
                "[todo <task_name>] - adds a todo task \n" +
                "[deadline <task_name> / deadline] - adds a deadline task \n" +
                "[event <event_name> / event_date] - adds an event \n" +
                "[done <task_num>] - marks a task as done \n" +
                "[bye bye] - exits duke \n" + LINE);
    }

    public static void printList(TaskList tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.print(String.valueOf(i + 1) + ". ");
            System.out.println(tasks.get(i));
        }
    }

    public static void printDone(String description) {
        System.out.println(LINE + "  Yay! You have done: " + description + "\n" + LINE);
    }
}
