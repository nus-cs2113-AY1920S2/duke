import java.util.Scanner;  // Import the Scanner class

public class Duke {

    public static void intro()
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

    public static void echoInput(String input) {
        System.out.println("----------");
        System.out.println(input);
        System.out.println("----------");
    }

    public static void main(String[] args) {
        intro();

        // Prepare for first input
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What can I do for you?");
        String userInput = inputScanner.nextLine();

        while(!userInput.equals("bye") && !userInput.equals("Bye")) {
            echoInput(userInput);
            System.out.println("Anything else? Remember that you can leave by typing 'bye'.");
            userInput = inputScanner.nextLine();
        }

        System.out.println("Goodbye!");
    }
}
