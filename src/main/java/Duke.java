import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = ".______     ______   .______   \n"
                + "|   _  \\   /  __  \\  |   _  \\  \n"
                + "|  |_)  | |  |  |  | |  |_)  | \n"
                + "|   _  <  |  |  |  | |   _  <  \n"
                + "|  |_)  | |  `--'  | |  |_)  | \n"
                + "|______/   \\______/  |______/  \n";
        String divider = "_________________________________________________";
        String welcomeMessage = "Hello human! I am Bob.\n" + "What can I do for you?";

        //Welcome and sign out messages
        System.out.println("Bob the chatbot\n" + logo);
        System.out.println(welcomeMessage);
        System.out.println(divider);

        Scanner command = new Scanner(System.in);
        String endCommand = "bye";
        String endMessage = "Bob thanks you for coming! See you again soon!";

        //Storing text
        String[] tasks = new String[100]; //Array of tasks
        int numTasks = 0;

        //List items
        String listCommand = "list";


        while (true) {
            String userInput = command.nextLine();
            if (userInput.equals(endCommand)) {
                System.out.println(divider);
                System.out.println(endMessage);
                System.out.println(divider);
                break;
            } else if (userInput.equals(listCommand)) {
                System.out.println(divider);
                for (int i = 1; i < numTasks+1; i++) {
                    String taskNum = Integer.toString(i);
                    System.out.println(taskNum + ". " + tasks[i-1]);
                }
                System.out.println(divider);
            } else {
                System.out.println(divider);
                tasks[numTasks] = userInput;
                numTasks++;
                System.out.println("added: " + userInput);
                System.out.println(divider);
            }
        }
    }
}
