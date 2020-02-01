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

    public static String taskValidator(int numTasks) {
        String totalTasks = Integer.toString(numTasks+1);
        if (numTasks == 1) {
            return "You now have " + totalTasks+ " task in the list!";
        } else {
            return "You now have " + totalTasks+ " tasks in the list!";
        }
    }

    public static void inputValidation(Task tasks[], int numTasks, String userCommand) {
        String[] words = userCommand.split(" ");
        int spacesPadding = 1;
        int taskPadding = 4;

        String todoCommand = "todo";
        String deadlineCommand = "deadline";
        String eventCommand = "event";
        String completeCommand = "done";

        if (words[0].equals(todoCommand)) {
            String todo = userCommand.substring(todoCommand.length() + spacesPadding);
            tasks[numTasks] = new Todo(todo);
            System.out.println("Got it! You've added a todo task: ");
            System.out.println(tasks[numTasks]);
            System.out.println(taskValidator(numTasks));
        } else if (words[0].equals(deadlineCommand)) {
            int indexOfBy = userCommand.indexOf("/by");
            String task = userCommand.substring(deadlineCommand.length() + spacesPadding, indexOfBy - 1);
            String byDate = userCommand.substring(indexOfBy + taskPadding);
            tasks[numTasks] = new Deadline(task, byDate);
            System.out.println("Got it! You've added a deadline task: ");
            System.out.println(tasks[numTasks]);
            System.out.println(taskValidator(numTasks));
        } else if (words[0].equals(eventCommand)) {
            int indexOfAt = userCommand.indexOf("/at");
            String task = userCommand.substring(eventCommand.length() + spacesPadding, indexOfAt - 1);
            String atDate = userCommand.substring(indexOfAt + taskPadding);
            tasks[numTasks] = new Event(task, atDate);
            System.out.println("Got it! You've added an event task: ");
            System.out.println(tasks[numTasks]);
            System.out.println(taskValidator(numTasks));
        } else if (words[0].equals(completeCommand)) {
            int taskNum = Integer.parseInt(words[1]);
            tasks[taskNum-1].markAsDone();
            printDoneTask(tasks, taskNum);
        }
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
            } else {
                System.out.println(DIVIDER);
                inputValidation(tasks, numTasks, userInput);
                numTasks++;
                System.out.println(DIVIDER);
            }
        }
    }
}
