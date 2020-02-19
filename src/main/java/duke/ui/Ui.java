package duke.ui;
import duke.task.TaskList;

import java.util.List;

/**
 * Class for user interface-related functions.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public static void displayWelcome()
    {
        // Logo generated using http://patorjk.com/software/taag/#p=display&f=Fire%20Font-s&t=NUSBOT
        String logo = "    )       (           )          \n"
                + " ( /(       )\\ )  (  ( /(   *   )  \n"
                + " )\\())   ( (()/(( )\\ )\\())` )  /(  \n"
                + "((_)\\    )\\ /(_))((_|(_)\\  ( )(_)) \n"
                + " _((_)_ ((_|_))((_)_  ((_)(_(_())  \n"
                + "| \\| | | | / __|| _ )/ _ \\|_   _|  \n"
                + "| .` | |_| \\__ \\| _ \\ (_) | | |    \n"
                + "|_|\\_|\\___/|___/|___/\\___/  |_|    \n"
                + "                                   \n";
        System.out.println(logo);
        System.out.println("Hi! Type 'bye' to leave at any time.");
        System.out.println("What can I do for you?");
    }

    /**
     * Surrounds the input string with bars and prints it.
     *
     * @param input String to print.
     */
    public static void formatPrint(String input) {
        System.out.println("----------");
        System.out.println(input);
        System.out.println("----------");
    }

    /**
     * Surrounds the input TaskList with bars and prints it.
     *
     * @param taskList TaskList to print.
     */
    public static void formatPrint(TaskList taskList) {
        System.out.println("----------");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i+1 + ". " + taskList.getTask(i));
        }
        System.out.println("----------");
    }

    /**
     * Surrounds the list of strings with bars and prints it.
     *
     * @param list List of strings to print.
     */
    public static void formatPrint(List<String> list) {
        System.out.println("----------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("----------");
    }

    /**
     * Print a message when the user does not input data in the correct format.
     *
     * @param taskType Whether the task is a Deadline or an Event.
     */
    public static void printCorrectDateFormat(String taskType) {
        switch (taskType) {
        case "deadline":
            Ui.formatPrint("deadline description /by yyy-mm-dd");
            break;
        case "event":
            Ui.formatPrint("event description /at yyyy-mm-dd");
            break;
        }
    }
}
