import java.util.Scanner;
public class Duke {

    public static String lineSeparator = "--------------------------------------------";
    public static Task[] tasks = new Task[100];
    public static int numOfTasks = 0;
    public static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        // Function that prints out the initial greeting for duke
        printGreeting();

        // Loop that keeps running the program until user exits
        while(true) {
            // Getting the user input every time from the user
            String userResponse = getUserResponse();
            String[] userResponseList = userResponse.split(" ");
            String action = userResponseList[0];
            String restOfUserInput = userResponse.replace(action, "").trim();

            if(action.equals("bye")){
                break;
            } else if(action.equals("list")){
                doListCommand();
            } else if(action.equals("done")){
                doDoneCommand(restOfUserInput);
            } else if(action.equals("todo")){
                doTodoCommand(restOfUserInput);
            } else if(action.equals("deadline")){
                doDeadlineCommand(restOfUserInput);
            } else if(action.equals("event")){
                doEventCommand(restOfUserInput);
            } else {
                System.out.println("Add exception here");
            }

        }
        printExitMessage();
    }

    // Helper function for printing line separator
    public static void printLineSeparator(){
        System.out.println(lineSeparator);
    }

    // Function for initial greeting of Duke
    public static void printGreeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        printLineSeparator();
    }

    // Helper function that gets the response from the user
    public static String getUserResponse() {
        String userResponse = userInput.nextLine();
        return userResponse.trim();
    }

    // Function that prints out action when user exits program
    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    // Helper function to print out message when task gets added to list
    public static void printTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[numOfTasks-1]);
        System.out.println("Now you have " + numOfTasks + " tasks in the list. ");
    }

    // Helper function that executes command for 'list'
    public static void doListCommand() {
        printLineSeparator();
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < numOfTasks; i++){
            System.out.println((i+1) + "." + tasks[i]);
        }
        printLineSeparator();
    }

    // Helper function that executes command for 'done'
    public static void doDoneCommand(String command){
        printLineSeparator();
        int taskNum = Integer.parseInt(command) - 1;
        tasks[taskNum].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskNum]);
        printLineSeparator();
    }

    // Helper function that executes command for 'todo'
    public static void doTodoCommand(String command){
        printLineSeparator();
        tasks[numOfTasks] = new Todo(command);
        numOfTasks++;
        printTask();
        printLineSeparator();
    }

    // Helper function that executes command for 'deadline'
    public static void doDeadlineCommand(String command){
        printLineSeparator();
        String[] taskList = command.split(" /by ");
        String task = taskList[0];
        String by = taskList[1];
        tasks[numOfTasks] = new Deadline(task, by);
        numOfTasks++;
        printTask();
        printLineSeparator();
    }

    // Helper function that executes command for 'event'
    public static void doEventCommand(String command){
        printLineSeparator();
        String[] taskList = command.split(" /at ");
        String task = taskList[0];
        String at = taskList[1];
        tasks[numOfTasks] = new Deadline(task, at);
        numOfTasks++;
        printTask();
        printLineSeparator();
    }
}


