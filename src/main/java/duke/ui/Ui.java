package duke.ui;
import duke.task.TaskList;

public class Ui {

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

    public static void formatPrint(String input) {
        System.out.println("----------");
        System.out.println(input);
        System.out.println("----------");
    }

    public static void formatPrint(TaskList taskList) {
        System.out.println("----------");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i+1 + ". " + taskList.getTask(i));
        }
        System.out.println("----------");
    }

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
