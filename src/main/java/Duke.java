import java.util.Scanner;

public class Duke {
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
            System.out.println("\t" + tasks[i].getID() + "." + tasks[i].getStatusIcon() + " " + tasks[i].getName());
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void addTask(String taskName, Task[] tasks){
        Task newTask = new Task(taskName);
        tasks[newTask.getID()] = newTask;
        System.out.println("    ____________________________________________________________");
        System.out.println("\tadded: " + newTask.getName());
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void markAsDone(String command, Task[] tasks){
        int taskID = getIdFromCommand(command);

        /* Exit if enter a wrong task id */
        if (taskID > Task.getNumberOfTasks() || taskID < 1){
            System.out.println("    ____________________________________________________________");
            System.out.println("\tWrong Number!");
            System.out.println("    ____________________________________________________________");
            System.out.println("\nPlease enter your command again or enter \"bye\" to exit:");
            return;
        }

        Task markedTask = tasks[taskID];
        markedTask.markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("\tI've marked this task as done: ");
        System.out.println("\t" + markedTask.getStatusIcon() + markedTask.getName());
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static int getIdFromCommand(String command){
        String[] words = command.split("\\s+");
        int ID = Integer.parseInt(words[1]);
        return ID;
    }

    public static void bye(){
        System.out.println("    ____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void echo(String command){
        System.out.println("    ____________________________________________________________");
        System.out.println("\t"+command);
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void main(String[] args) {
        String logo = "\t _____    _____   __   _   ______  _____  \n"
                +"\t|  _  \\  | ____| |  \\ | | |___  / /  _  \\ \n"
                +"\t| |_| |  | |__   |   \\| |    / /  | | | | \n"
                +"\t|  _  /  |  __|  | |\\   |   / /   | | | | \n"
                +"\t| | \\ \\  | |___  | | \\  |  / /__  | |_| | \n"
                +"\t|_|  \\_\\ |_____| |_|  \\_| /_____| \\_____/ \n";

        /* Max number of tasks is 100 (task's index starts with 1) */
        Task[] tasks = new Task[101];

        /* Greet to user */
        greet(logo);

        /* Enable to get command from Command Line */
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        /* Process command */
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

        /* Exit */
        bye();
    }
}
