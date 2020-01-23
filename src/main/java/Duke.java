import java.util.Scanner;  // User input
import java.util.ArrayList;
import java.util.List;

public class Duke {

    static List<String> dukeList = new ArrayList<String>();

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
        for (int i = 0; i < dukeList.size(); i++) {
            System.out.println(i+1 + ". " + dukeList.get(i));
        }
        System.out.println("----------");
    }

    public static void main(String[] args) {
        intro();

        // Prepare for first input
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What can I do for you?");
        String userInput = inputScanner.nextLine();

        while(!userInput.equals("bye") && !userInput.equals("Bye")) {

            if (userInput.equals("list")) {
                printList();
            } else {
                dukeList.add(userInput); // Add task to list of things
                formatPrint("Added: " + userInput);
            }

            System.out.println("Anything else? Remember that you can leave by typing 'bye'.");
            userInput = inputScanner.nextLine(); // Prepare for next user input
        }
        System.out.println("Goodbye!");
    }
}
