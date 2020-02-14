import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    //private static int counter = 0;
    public static final int MAXIMUM_TASKS = 100;
    public static final String[] VALID_COMMANDS = {"done","list","bye","todo","deadline","event"};
    //private static Task[] listOfTasks =  new Task[MAXIMUM_TASKS];
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static final String logo = "  ____        _        \n"
                                 + " |  _ \\ _   _| | _____ \n"
                                 + " | | | | | | | |/ / _ \\\n"
                                 + " | |_| | |_| |   <  __/\n"
                                 + " |____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        showWelcomeMessage();
        run();
    }

    private static void showWelcomeMessage() {
        printLine();
        System.out.println(logo);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printLine();
    }

    private static void printLine() {
        for (int i = 0; i < 60; i += 1) {
            System.out.print("_");
        }
        System.out.println();
    }

    private static String getInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    private static String getCommand(String line) throws InvalidCommandException {
        int dividerPosition = line.indexOf(" ");
        String command;
        if (dividerPosition != -1) {
            command = line.substring(0, dividerPosition);
        } else {
            command = line;
        }
        boolean isExists = false;
        for (String temp: VALID_COMMANDS) {
            if (temp.equals(command)) {
                isExists = true;
                break;
            }
        }
        if (isExists) {
            return command;
        } else {
            throw new InvalidCommandException();
        }
    }

    public static void exitFromApp() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
        System.exit(0);
    }

    public static String getTaskInformation(String line) throws IndexOutOfBoundsException {
        int dividerPosition = line.indexOf(" ");
        if (dividerPosition == -1) {
            throw new IndexOutOfBoundsException();
        } else {
            return line.substring(dividerPosition+1);
        }
    }

    public static void handleInvalidCommand() {
        printLine();
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printLine();
    }

    private static void handleIndexOutOfBounds(String command) {
        printLine();
        System.out.println(" ☹ OOPS!!! The description of a " + command + " cannot be empty.");
        printLine();
    }

    private static void run() {
        while (true) {
            String line = getInput();
            String command;
            try {
                command = getCommand(line);
            } catch (InvalidCommandException c) {
                handleInvalidCommand();
                continue;
            }
            switch (command) {
            case "bye":
                exitFromApp();
                break;
            case "list":
                listAllTasks();
                break;
            case "done":
                markTaskAsDone(line);
                break;
            default:
                String taskInformation;
                try {
                    taskInformation = getTaskInformation(line);
                } catch (IndexOutOfBoundsException b) {
                    handleIndexOutOfBounds(command);
                    continue;
                }
                storeTaskIntoList(taskInformation, command);
                break;
            }
        }
    }

    public static void storeTaskIntoList(String taskInformation, String separator, String command) {
        int dividerPosition = taskInformation.indexOf(separator);
        String description = taskInformation.substring(0, dividerPosition);
        String dateAndTime = taskInformation.substring(dividerPosition+5);
        if (command.equals("deadline")) {
            Deadline deadlineToAdd = new Deadline(description, dateAndTime);
            listOfTasks.add(deadlineToAdd);
        } else if (command.equals("event")) {
            Event eventToAdd = new Event(description, dateAndTime);
            listOfTasks.add(eventToAdd);
        }

    }

    public static void storeTaskIntoList(String taskInformation, String command) {
        Task taskToStore;
        switch (command) {
        case "todo":
            Todo todoToAdd = new Todo(taskInformation);
            listOfTasks.add(todoToAdd);
            break;
        case "deadline":
            storeTaskIntoList(taskInformation, " /by ", command);
            break;
        case "event":
            storeTaskIntoList(taskInformation, " /at ", command);
            break;
        }
        //counter += 1;
        showStoredTask();
    }

    private static void showStoredTask() {
        int sizeOfList = listOfTasks.size();
        Task lastTask = listOfTasks.get(sizeOfList - 1);

        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + lastTask.getTypeIcon()
                +"[" + lastTask.getStatusIcon() + "]"
                + " " + lastTask.showFullDescription());

        System.out.println(" Now you have " + sizeOfList + " tasks in the list.");
        printLine();
    }

    public static void listAllTasks() {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        /*for (int i = 0; i < counter; i += 1) {
            System.out.print(" " + (i+1) + "." + listOfTasks[i].getTypeIcon() + "["
                    + listOfTasks[i].getStatusIcon() + "] ");
            System.out.println(listOfTasks[i].showFullDescription());
        }*/
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currTask = listOfTasks.get(i);
            System.out.print(" " + (i + 1) + "." + currTask.getTypeIcon() + "["
                    + currTask.getStatusIcon() + "] ");
            System.out.println(currTask.showFullDescription());
        }

        printLine();
    }

    public static void markTaskAsDone(String line) {
        int dividerPosition = line.indexOf("done");
        int taskNumber = Integer.parseInt(line.substring(dividerPosition+5));
        Task taskDone = listOfTasks.get(taskNumber - 1);
        taskDone.markAsDone();
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.print("   [" + taskDone.getStatusIcon() + "] ");
        System.out.println(taskDone.showFullDescription());
        printLine();
    }
}
