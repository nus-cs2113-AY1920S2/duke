import java.util.Scanner;

public class Jan {

    public static final int MAXIMUM_TASK = 100;
    public static Task[] Tasks = new Task[MAXIMUM_TASK];

    public static void executeInstruction(String command, String commandDescription) throws MissingDescriptionException,
            UnknownCommandException{
        System.out.println("____________________________________________________________");
        switch(command) {
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getTotalTask(); i++) {
                System.out.println(Tasks[i].getTaskNum() + ". " + Tasks[i]);
            }
            break;
        case "done":
            if (commandDescription.isEmpty()) {
                throw new MissingDescriptionException();
            }
            int taskNum = Integer.parseInt(commandDescription) - 1;
            if (existTask(taskNum)) {
                System.out.println("Nice! I've marked this task as done: ");
                Tasks[taskNum].setDone(true);
                System.out.println(Tasks[taskNum]);
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
        default:
            throw new UnknownCommandException();
        }
        System.out.println("____________________________________________________________");
    }

    private static boolean existTask(int taskNum){
        return taskNum < Task.getTotalTask();
    }

    private static void addNewTask(String command, String commandDescription, String divider) {
        String[] taskDetails = commandDescription.split(divider);
        switch(command) {
        case "todo":
            Todo todo = new Todo(commandDescription);
            Tasks[Task.getTotalTask() - 1] = todo;
            System.out.println("Got it. I've added this task:\n " + todo);
            break;
        case "deadline":
            Deadline deadline = new Deadline(taskDetails[0],taskDetails[1]);
            Tasks[Task.getTotalTask() - 1] = deadline;
            System.out.println("Got it. I've added this task:\n " + deadline);
            break;
        case "event":
            Event event = new Event(taskDetails[0],taskDetails[1]);
            Tasks[Task.getTotalTask() - 1] = event;
            System.out.println("Got it. I've added this task:\n " + event);
            break;
        }
    }

    private static void printCurrentTaskCount() {
        if (Task.getTotalTask() < 2){
            System.out.println("Now you have " + Task.getTotalTask() + " task in the list");
        } else {
            System.out.println("Now you have " + Task.getTotalTask() + " tasks in the list");
        }
    }

    private static void printMissingTaskDetailMessage() {
        System.out.println("Invalid request due to missing details. type \"help\" to find out more");
    }

    private static void printUnknownCommandMessage() {
        System.out.println("Try using the following commands:\n"
                + "list                             -- to get a list of all the existing tasks\n"
                + "done [item number]               -- mark task as completed"
                + "todo [item]                      -- add new todo task\n"
                + "deadline [item] /by [date][time] -- add new deadline task\n"
                + "event [item] /at [date][time]    -- add new event task\n");
    }

    private static void printGreetingMessage(String logo) {
        System.out.println(logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Jan\n" + " What can I do for you?");
        System.out.println("____________________________________________________________");
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

        System.out.println("Bye. Hope to see you again soon!");
    }
}
