import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static String lineSeparator = "--------------------------------------------";
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) throws DukeException {

        // Function that prints out the initial greeting for duke
        printGreeting();

        // Loop that keeps running the program until user exits
        while (true) {
            // Getting the user input every time from the user
            String userResponse = getUserResponse();
            String[] userResponseList = userResponse.split(" ");
            String action = userResponseList[0];
            String restOfUserInput = userResponse.replace(action, "").trim();
            try {
                if (action.equals("bye")) {
                    break;
                } else if (action.equals("list")) {
                    doListCommand();
                } else if (action.equals("done")) {
                    doDoneCommand(restOfUserInput);
                } else if (action.equals("todo")) {
                    try {
                        doTodoCommand(restOfUserInput);
                    } catch (DukeException e) {
                        System.out.println("Error. Todo command must have input!");
                        printLineSeparator();
                    }
                } else if (action.equals("deadline")) {
                    doDeadlineCommand(restOfUserInput);
                } else if (action.equals("event")) {
                    doEventCommand(restOfUserInput);
                } else if (action.equals("delete")){
                    doDeleteCommand(restOfUserInput);
                } else {
                    throw new DukeException();

                }

            } catch (DukeException e) {
                printLineSeparator();
                System.out.println("Invalid command. Please try again! ");
                printLineSeparator();
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
        System.out.println(tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list. ");
    }

    // Helper function that executes command for 'list'
    public static void doListCommand() {
        printLineSeparator();
        System.out.println("Here are the tasks in your list:");
        // OPTIMIZE THIS FOR LOOP
        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i+1) + "." + tasks.get(i));
        }
        printLineSeparator();
    }

    // Helper function that executes command for 'done'
    public static void doDoneCommand(String command){
        printLineSeparator();
        int taskNum = Integer.parseInt(command) - 1;
        tasks.get(taskNum).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNum));
        printLineSeparator();
        saveTasks();
    }

    // Helper function that executes command for 'todo'
    public static void doTodoCommand(String command) throws DukeException{
        printLineSeparator();
        if (command.isEmpty()){
            throw new DukeException();
        } else{
            tasks.add(new Todo(command));
            printTask();
            printLineSeparator();
            saveTasks();
        }

    }

    // Helper function that executes command for 'deadline'
    public static void doDeadlineCommand(String command){
        printLineSeparator();
        String[] taskList = command.split(" /by ");
        String task = taskList[0];
        String by = taskList[1];
        tasks.add(new Deadline(task, by));
        printTask();
        printLineSeparator();
        saveTasks();
    }

    // Helper function that executes command for 'event'
    public static void doEventCommand(String command){
        printLineSeparator();
        String[] taskList = command.split(" /at ");
        String task = taskList[0];
        String at = taskList[1];
        tasks.add(new Event(task, at));
        printTask();
        printLineSeparator();
        saveTasks();
    }

    // Helper function that saves files to hard disk
    public static void saveTasks(){
        try {
            String filepath = "./data/duke.txt";
            FileWriter fw = new FileWriter(filepath);
            String textToAdd = TasktoString();
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e){
            System.out.println("There was an error trying to save file. Check if file exists!");
        }
    }

    // Helper function that turns content of Task into String
    public static String TasktoString(){
        String taskString = "";
        for(int i = 0; i < tasks.size(); i++){
            taskString += tasks.get(i) + "\n";
        }
        return taskString;
    }

    // Helper function that executes command for 'delete'
    public static void doDeleteCommand(String command){
        printLineSeparator();
        int taskNum = Integer.parseInt(command) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(taskNum));
        tasks.remove(tasks.get(taskNum));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLineSeparator();
    }
}


