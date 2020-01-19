import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = ".______     ______   .______   \n"
                + "|   _  \\   /  __  \\  |   _  \\  \n"
                + "|  |_)  | |  |  |  | |  |_)  | \n"
                + "|   _  <  |  |  |  | |   _  <  \n"
                + "|  |_)  | |  `--'  | |  |_)  | \n"
                + "|______/   \\______/  |______/  \n";
        String divider = "-----------------------------------------------";
        String welcomeMessage = "Hello human! I am Bob.\n" + "What can I do for you?";

        System.out.println("Bob the chatbot\n" + logo);
        System.out.println(welcomeMessage);
        System.out.println(divider);

        Scanner command = new Scanner(System.in);
        String endCommand = "bye";
        String endMessage = "Bob thanks you for coming! See you again soon!";

        while (true) {
            String userInput = command.nextLine();
            if (userInput.equals(endCommand)) {
                System.out.println(divider);
                System.out.println(endMessage);
                System.out.println(divider);
                break;
            } else {
                System.out.println(divider);
                System.out.println(userInput);
                System.out.println(divider);
            }
        }
    }
}
