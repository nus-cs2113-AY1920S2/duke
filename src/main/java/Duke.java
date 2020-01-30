import java.util.Scanner;

public class Duke {

    public static final int OFFSITE_OF_TIME = 4;
    public static final int MAX_NUMBER_OF_TASKS = 100;

    public static void greet(String logo){
        System.out.println("    ____________________________________________________________");
        System.out.println(logo);
        System.out.println("\tHello! I'm Renzo");
        System.out.println("\tWhat can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void list(Task[] tasks){
        System.out.println("    ____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        int numberOfTasks = Task.getNumberOfTasks();
        for (int i = 1; i <= numberOfTasks; i++) {
            System.out.println("\t" + tasks[i].getID() + "." + tasks[i].toString());
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static Todo processToDoDescription(String description){
        return new Todo(description.replace("todo ",""));
    }

    public static Deadline processDeadlineDescription(String description){
        String deadlineDescription = description.replace("deadline ","");
        int indexOfBy = deadlineDescription.indexOf("/by");
        int nameStartIndex = 0;
        int nameEndIndex = indexOfBy - 1;
        int timeStartIndex = indexOfBy + OFFSITE_OF_TIME;
        String name = deadlineDescription.substring(nameStartIndex, nameEndIndex);
        String time = deadlineDescription.substring(timeStartIndex);
        return new Deadline(name, time);
    }

    public static Event processEventDescription(String description){
        String eventDescription = description.replace("event ","");
        int indexOfAt = eventDescription.indexOf("/at");
        int nameStartIndex = 0;
        int nameEndIndex = indexOfAt - 1;
        int timeStartIndex = indexOfAt + OFFSITE_OF_TIME;
        String name = eventDescription.substring(nameStartIndex, nameEndIndex);
        String time = eventDescription.substring(timeStartIndex);
        return new Event(name, time);
    }

    public static void addTask(String description, Task[] tasks){
        Task newTask;
        boolean isToDo = description.contains("todo");
        boolean isDeadline = description.contains("deadline");
        boolean isEvent = description.contains("event");

        if (isToDo){
            newTask = processToDoDescription(description);
        } else if (isDeadline){
            newTask = processDeadlineDescription(description);
        } else if (isEvent){
            newTask = processEventDescription(description);
        } else {
            newTask = new Task(description);
        }

        tasks[newTask.getID()] = newTask;
        printAddedTask(newTask);
    }

    public static void printAddedTask(Task newTask) {
        System.out.println("    ____________________________________________________________");
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t" + newTask.toString());
        System.out.printf("\tNow you have %d task(s) in the list\n", Task.getNumberOfTasks());
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void markAsDone(String command, Task[] tasks){
        int taskID = getIdFromCommand(command);

        /* Exit if enter a wrong task id */
        boolean isWrongID = taskID > Task.getNumberOfTasks() || taskID < 1;
        if (isWrongID){
            printIdOutOfRangeError();
            return;
        }

        Task taskToBeMarked = tasks[taskID];
        taskToBeMarked.markAsDone();
        printMarkedTask(taskToBeMarked);
    }

    public static void printMarkedTask(Task markedTask) {
        System.out.println("    ____________________________________________________________");
        System.out.println("\tI've marked this task as done: ");
        System.out.println("\t" + markedTask.getStatusIcon() + markedTask.getName());
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void printIdOutOfRangeError() {
        System.out.println("    ____________________________________________________________");
        System.out.println("\tWrong Number!");
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command again or enter \"bye\" to exit:");
    }

    public static int getIdFromCommand(String command){
        String[] words = command.split("\\s+");
        return Integer.parseInt(words[1]);
    }

    public static void bye(){
        System.out.println("    ____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void echo(String command){
        System.out.println("    ____________________________________________________________");
        System.out.println("\t" + command);
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void main(String[] args) {

        /* A logo for Renzo */
        String logo = "\t _____    _____   __   _   ______  _____  \n"
                +"\t|  _  \\  | ____| |  \\ | | |___  / /  _  \\ \n"
                +"\t| |_| |  | |__   |   \\| |    / /  | | | | \n"
                +"\t|  _  /  |  __|  | |\\   |   / /   | | | | \n"
                +"\t| | \\ \\  | |___  | | \\  |  / /__  | |_| | \n"
                +"\t|_|  \\_\\ |_____| |_|  \\_| /_____| \\_____/ \n";


        Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];

        /* Greet to user */
        greet(logo);

        /* Enable to get command from Command Line */
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        /* Process command */
        processCommand(tasks, scanner, command);

        /* Exit */
        bye();
    }

    public static void processCommand(Task[] tasks, Scanner scanner, String command) {
        while (!command.equals("bye")) {
            if (command.equals("list")) {
               list(tasks);
            } else if (command.contains("done")) {
                markAsDone(command, tasks);
            } else {
                addTask(command, tasks);
            }
            command = scanner.nextLine();
        }
    }
}
