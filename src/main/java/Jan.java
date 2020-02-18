import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;

public class Jan {

    public static final String DIVIDER = "____________________________________________________________";
    public static ArrayList<Task> Tasks = new ArrayList<>();

    public static void executeInstruction(String command, String commandDescription) throws MissingDescriptionException,
            UnknownCommandException{
        System.out.println(DIVIDER);
        switch(command) {
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Tasks.size(); i++) {
                System.out.println( i + 1 + ". " + Tasks.get(i));
            }
            break;
        case "done":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            int taskNum = Integer.parseInt(commandDescription) - 1;
            if (existTask(taskNum)) {
                System.out.println("Nice! I've marked this task as done: ");
                Tasks.get(taskNum).setDone(true);
                System.out.println(Tasks.get(taskNum));
            }else{
                System.out.println("Jan cannot find this task");
            }
            break;
        case "todo":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            addNewTask(command, commandDescription,"");
            printCurrentTaskCount();

            break;
        case "deadline":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            addNewTask(command, commandDescription, "/by");
            printCurrentTaskCount();
            break;
        case "event":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            addNewTask(command, commandDescription,"/at");
            printCurrentTaskCount();
            break;
        case "delete":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            taskNum = Integer.parseInt(commandDescription) - 1;
            if (existTask(taskNum)) {
                Task tempTask = Tasks.get(taskNum);
                Tasks.remove(taskNum);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(tempTask);
                printCurrentTaskCount();
            }else{
                System.out.println("Jan cannot find this task");
            }
            break;
        default:
            throw new UnknownCommandException();
        }
        System.out.println(DIVIDER);
    }

    private static boolean existTask(int taskNum){
        return taskNum < Tasks.size();
    }

    private static void addNewTask(String command, String commandDescription, String divider) {
        String[] taskDetails = commandDescription.split(divider);
        switch(command) {
        case "todo":
            Todo todo = new Todo(commandDescription);
            Tasks.add(todo);
            System.out.println("Got it. I've added this task:\n " + todo);
            break;
        case "deadline":
            Deadline deadline = new Deadline(taskDetails[0],taskDetails[1]);
            Tasks.add(deadline);
            System.out.println("Got it. I've added this task:\n " + deadline);
            break;
        case "event":
            Event event = new Event(taskDetails[0],taskDetails[1]);
            Tasks.add(event);
            System.out.println("Got it. I've added this task:\n " + event);
            break;
        }
    }

    private static void printCurrentTaskCount() {
        if (Tasks.size() < 2){
            System.out.println("Now you have " + Tasks.size() + " task in the list");
        } else {
            System.out.println("Now you have " + Tasks.size() + " tasks in the list");
        }
    }

    private static void printMissingTaskDetailMessage() {
        System.out.println("Invalid request due to missing details. type \"help\" to find out more");
        System.out.println(DIVIDER);
    }

    private static void printUnknownCommandMessage() {
        System.out.println("Try using the following commands:\n"
                + "list                             -- to get a list of all the existing tasks\n"
                + "done [item number]               -- mark task as completed\n"
                + "todo [item]                      -- add new todo task\n"
                + "deadline [item] /by [date][time] -- add new deadline task\n"
                + "event [item] /at [date][time]    -- add new event task");
        System.out.println(DIVIDER);
    }

    private static void printGreetingMessage(String logo) {
        System.out.println(logo);
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Jan\n" + " What can I do for you?");
        System.out.println(DIVIDER);
    }

    private static void printGoodbyeMessage(){
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {

        String logo = " ________     _____     _____     __\n"
                + "|_____   |  /  ___  \\  |     \\   |  | \n"
                + "      |  | |  /   \\  | |  |\\  \\  |  |\n"
                + " __   |  | |  |___|  | |  | \\  \\ |  |\n"
                + "|  |__|  | |   ___   | |  |  \\  \\|  |\n"
                + "|________/ |__|   |__| |__|   \\_____|\n";
        printGreetingMessage(logo);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            String[] requests = line.split(" ", 2);
            try {
                if (requests.length > 1) {
                    executeInstruction(requests[0], requests[1]);
                } else {
                    executeInstruction(requests[0], "");
                }
            }catch (MissingDescriptionException e) {
                printMissingTaskDetailMessage();
            } catch (UnknownCommandException e) {
                printUnknownCommandMessage();
            }
            line = in.nextLine();
        }
        printGoodbyeMessage();
    }
}
