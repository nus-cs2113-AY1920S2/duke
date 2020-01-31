import java.util.Scanner;  // User input
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Duke {

    static List<Task> taskList = new ArrayList<Task>();

    private static void intro()
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
        System.out.println("Hello from\n" + logo);
        System.out.println("Type 'bye' to leave at any time.");
    }

    private static void formatPrint(String input) {
        System.out.println("----------");
        System.out.println(input);
        System.out.println("----------");
    }

    private static void printList() {
        System.out.println("----------");
        for (int i = 0; i < taskList.size(); i++) {
            //String desc = taskList.get(i).getDescription();
            //System.out.println(i+1 + ". [" + taskList.get(i).getStatusIcon() + "] " + desc);
            System.out.println(i+1 + ". " + taskList.get(i));
        }
        System.out.println("----------");
    }

    public static void main(String[] args) {
        intro();

        // Prepare for first input
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What can I do for you?");

        // Note: Scanner.next() reads until delimiter, Scanner.nextLine() reads until EOL
        String userCommand = inputScanner.next(); // Read first word of input
        String userParams = inputScanner.nextLine(); // Get user input after first word

        // Main execution loop
        while(!userCommand.equals("bye") && !userCommand.equals("Bye")) {
            switch (userCommand) {
            case "list":
                printList();
                break;
            case "todo":
                taskList.add(new Todo(userParams));
                formatPrint("Added todo:" + userParams);
                break;
            case "deadline":
            case "event":
                int delimIndex = userParams.indexOf("/"); // Duke uses / to define where the date starts
                String desc = userParams.substring(0, delimIndex); // Get description substring (before /)
                String date = userParams.substring(delimIndex+1, userParams.length()); // Get date substring (after /)
                if (userCommand.equals("deadline")) {
                    taskList.add(new Deadline(desc, date));
                    formatPrint("Added task: " + desc + "| deadline: " + date);
                } else {
                    taskList.add(new Event(desc, date));
                    formatPrint("Added event: " + desc + "| on/at: " + date);
                }
                break;
            case "done":
                String taskId = userParams.replaceAll("[^0-9]", ""); // Replace all except numbers
                System.out.println(taskId);
                taskList.get(Integer.parseInt(taskId) - 1).markAsDone(); // Mark task with that ID as done
                break;
            default:
                formatPrint("Sorry, I didn't recognize that command.");
            }

            System.out.println("You have " + taskList.size() + " task/s. (type 'list' to list your tasks)");

            System.out.println("Anything else? Remember that you can leave by typing 'bye'.");
            userCommand = inputScanner.next(); // Prepare for next user command
            userParams = inputScanner.nextLine(); // Get user input after first word
        }
        System.out.println("Goodbye!");
    }
}