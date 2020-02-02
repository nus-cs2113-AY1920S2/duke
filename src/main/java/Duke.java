import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    public static final int LENGTH_DEADLINE = 9;
    public static final int LENGTH_EVENT = 6;
    public static final int LENGTH_TODO = 5;



    public static void getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
    }

    public static void completeTask(ArrayList<Task> tasks, int taskIndex) {
        taskIndex--; // index starts from 0, unlike listing number
        if ( (taskIndex < tasks.size()) || (taskIndex > 0)) { // check if out of bounce
            Task currentTask = tasks.get(taskIndex);
            if (currentTask.getStatus()) { // check if already completed
                System.out.println("Task already completed!\n");
            } else {
                currentTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println( "["+ currentTask.getTaskType() + "][" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + "\n");
            }
        } else {
            System.out.println("Error: No such index in use\n");
        }
    }

    public static void printList(ArrayList<Task> tasks) {
        int count = 1;
        System.out.println("Listing tasks below:");

        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment!");
        } else {
            for (Task currentTask : tasks) {
                System.out.println("["+ currentTask.getTaskType() + "][" + currentTask.getStatusIcon() + "] " + count + ". " + currentTask.getDescription());
                count++;
            }
        }
        System.out.println("");
    }

    public static void printHelp() {
        System.out.println("Commands: ");
        System.out.println("List: lists all recorded tasks \nusage: list\n");
        System.out.println("Done: mark task as completed \nusage: done <task number>\n");
        System.out.println("To add new task, just type it out \nAvoid using other keywords as the first word\n");
        System.out.println("");
    }

    private static void printWelcomeMessage() {
        String logo =   "  ,--,       ,---,   .--.--.       ,---,        \n"
                + ",--.'|    ,`--.' |  /  /    '.    '  .' \\       \n"
                + "|  | :    |   :  : |  :  /`. /   /  ;    '.     \n"
                + ":  : '    :   |  ' ;  |  |--`   :  :       \\    \n"
                + "|  ' |    |   :  | |  :  ;_     :  |   /\\   \\   \n"
                + "'  | |    '   '  ;  \\  \\    `.  |  :  ' ;.   : \n"
                + "|  | :    |   |  |   `----.   \\ |  |  ;/  \\   \\ \n"
                + "'  : |__  '   :  ;   __ \\  \\  | '  :  | \\  \\ ,' \n"
                + "|  | '.'| |   |  '  /  /`--'  / |  |  '  '--'   \n"
                + ";  :    ; '   :  | '--'.     /  |  :  : \n"
                + "|  ,   /  ;   |.'    `--'---'   |  | ,'\n"
                + "---`-'   '---'                 `--''   \n";

        System.out.println("\n" + logo + "\nYour Lifestyle Scheduling Assistant\n");
        System.out.println("type \"help\" for list of commands");
        System.out.println("____________________________________________________________\n\nCurrent time: ");
        getDateTime();
        System.out.println("____________________________________________________________");
    }

    public static void addDeadline(ArrayList<Task> tasks, String taskDescription, int taskCounter) {
        String itemName = taskDescription.substring(LENGTH_DEADLINE);
        Task newTask = new Deadline(itemName);
        tasks.add(newTask);
        printAddedMessage(newTask, taskCounter);
    }

    public static void addEvent(ArrayList<Task> tasks, String taskDescription, int taskCounter) {
        String itemName = taskDescription.substring(LENGTH_EVENT);
        Task newTask = new Event(itemName);
        tasks.add(newTask);
        printAddedMessage(newTask, taskCounter);
    }

    public static void addTodo(ArrayList<Task> tasks, String taskDescription, int taskCounter) {
        String itemName = taskDescription.substring(LENGTH_TODO);
        Task newTask = new ToDo(itemName);
        tasks.add(newTask);
        printAddedMessage(newTask, taskCounter);
    }

    public static void printAddedMessage(Task task, int taskCounter) {
        System.out.println("The following task has been added\n[" + task.getTaskType() +"][" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("\nYou've got " + taskCounter + " task(s) in the list!\n");
    }

    public static void main(String[] args) {

        printWelcomeMessage();

        ArrayList<Task> tasks = new ArrayList<Task>();
        int taskCounter = 0;

        Scanner input = new Scanner(System.in);
        String userCommand = input.nextLine();

        while (!userCommand.equals("bye")){
            String[] words = userCommand.split(" ");
            int wordLength = words.length;
            switch (words[0]) {
            case "list":
                printList(tasks);
                break;
            case "done":
                if (wordLength != 2) {
                    System.out.println("Wrong format for command \"done\"");
                    break;
                }
                try {
                    int index = Integer.parseInt(words[1]);
                    completeTask(tasks, index);
                } catch (Exception e) {
                    System.out.println("Please input a valid number\n");
                }
                break;
            case "help":
                printHelp();
                break;
            case "todo":
                taskCounter++;
                addTodo(tasks, userCommand, taskCounter);
                break;
            case "event":
                taskCounter++;
                addEvent(tasks, userCommand, taskCounter);
                break;
            case "deadline":
                taskCounter++;
                addDeadline(tasks, userCommand, taskCounter);
                break;
            default:
                System.out.println("Please add the task type\n");
            }
            // end of current listening loop, preparing next command
            userCommand = input.nextLine();
        }
        System.out.println("LISA: Bye, hope to see you again!");
    }
}
