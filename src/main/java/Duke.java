import java.util.Scanner;

public class Duke {

    public static final String DIVIDER = "_________________________________________________";

    public static void printTaskList(Task[] tasks, int numTasks) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks on your list: ");
        for (int i = 1; i < numTasks+1; i++) {
            String taskNum = Integer.toString(i);
            System.out.println(taskNum + "." + tasks[i-1]);
        }
        System.out.println(DIVIDER);
    }

    public static void printDoneTask(Task[] tasks, int taskNum) {
        System.out.println(DIVIDER);
        System.out.println("Awesome! I've marked the following task as done:");
        System.out.println(tasks[taskNum-1]);
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        String logo = ".______     ______   .______   \n"
                + "|   _  \\   /  __  \\  |   _  \\  \n"
                + "|  |_)  | |  |  |  | |  |_)  | \n"
                + "|   _  <  |  |  |  | |   _  <  \n"
                + "|  |_)  | |  `--'  | |  |_)  | \n"
                + "|______/   \\______/  |______/  \n";
        String welcomeMessage = "Hello human! I am Bob.\n" + "What can I do for you?";

        //Welcome and sign out messages
        System.out.println("Bob the chatbot\n" + logo);
        System.out.println(welcomeMessage);
        System.out.println(DIVIDER);

        Scanner command = new Scanner(System.in);
        String endCommand = "bye";
        String endMessage = "Bob thanks you for coming! See you again soon!";

        //Storing text
        Task[] tasks = new Task[100]; //Array of objects
        int numTasks = 0;

        String listCommand = "list";
        String completeCommand = "done";

        while (true) {
            String userInput = command.nextLine();
            String[] words = userInput.split(" ");
            if (userInput.equals(endCommand)) {
                System.out.println(DIVIDER);
                System.out.println(endMessage);
                System.out.println(DIVIDER);
                break;
            } else if (userInput.equals(listCommand)) {
                printTaskList(tasks, numTasks);
            } else if (words[0].equals(completeCommand)) {
                int taskNum = Integer.parseInt(words[1]);
                tasks[taskNum-1].markAsDone();
                printDoneTask(tasks, taskNum);
            } else {
                System.out.println(DIVIDER);
                Task t = new Task(userInput);
                tasks[numTasks] = t;
                numTasks++;
                System.out.println("added: " + userInput);
                System.out.println(DIVIDER);
            }
        }
    }
}
