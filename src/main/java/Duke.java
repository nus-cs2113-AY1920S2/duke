import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Create Scanner object
        Scanner myScanner = new Scanner(System.in);

        // Create Hello message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String userInput = "";
        String NEW_LINE = "____________________________________________________________";

        // Get user input in a loop
        while(!userInput.equals("bye")){
            System.out.print("USER:");
            userInput = myScanner.nextLine();
            System.out.println(NEW_LINE);
            System.out.println("DUKE:" + userInput);
            System.out.println(NEW_LINE);
        }

        System.out.println("Exiting DUKE\n" + logo);
    }
}
