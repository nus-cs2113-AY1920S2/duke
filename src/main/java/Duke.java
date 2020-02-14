import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static int counter = 0;
    public static final int MAXIMUM_TASKS = 100;
    public static final String[] VALID_COMMANDS = {"done","list","bye","todo","deadline","event"};
    private static Task[] listOfTasks =  new Task[MAXIMUM_TASKS];
    private static final String logo = "  ____        _        \n"
                                 + " |  _ \\ _   _| | _____ \n"
                                 + " | | | | | | | |/ / _ \\\n"
                                 + " | |_| | |_| |   <  __/\n"
                                 + " |____/ \\__,_|_|\\_\\___|\n";
    static String currDir = System.getProperty("user.dir");
    private final static String filePath = currDir + "/duke.txt";

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

                try {
                    saveToFile();
                } catch (IOException e) {
                    System.out.println("IO Exception occurred: " + e.getMessage());
                }

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
                try {
                    saveToFile();
                } catch (IOException e) {
                    System.out.println("IO Exception occurred: " + e.getMessage());
                }
                break;
            }
        }
    }

    public static void storeTaskIntoList(String taskInformation, String separator, String command) {
        int dividerPosition = taskInformation.indexOf(separator);
        String description = taskInformation.substring(0, dividerPosition);
        String dateAndTime = taskInformation.substring(dividerPosition+5);
        if (command.equals("deadline")) {
            listOfTasks[counter] = new Deadline(description, dateAndTime);
        } else if (command.equals("event")) {
            listOfTasks[counter] = new Event(description, dateAndTime);
        }

    }

    public static void storeTaskIntoList(String taskInformation, String command) {
        switch (command) {
        case "todo":
            listOfTasks[counter] = new Todo(taskInformation);
            break;
        case "deadline":
            storeTaskIntoList(taskInformation, " /by ", command);
            break;
        case "event":
            storeTaskIntoList(taskInformation, " /at ", command);
            break;
        }
        counter += 1;
        showStoredTask();
    }

    private static void showStoredTask() {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + listOfTasks[counter-1].getTypeIcon()
                +"[" + listOfTasks[counter-1].getStatusIcon() + "]"
                + " " + listOfTasks[counter-1].showFullDescription());
        System.out.println(" Now you have " + counter + " tasks in the list.");
        printLine();
    }

    public static void listAllTasks() {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < counter; i += 1) {
            System.out.print(" " + (i+1) + "." + listOfTasks[i].getTypeIcon() + "["
                    + listOfTasks[i].getStatusIcon() + "] ");
            System.out.println(listOfTasks[i].showFullDescription());
        }
        printLine();
    }

    public static void markTaskAsDone(String line) {
        int dividerPosition = line.indexOf("done");
        int taskNumber = Integer.parseInt(line.substring(dividerPosition+5));
        listOfTasks[taskNumber-1].markAsDone();
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.print("   [" + listOfTasks[taskNumber-1].getStatusIcon() + "] ");
        System.out.println(listOfTasks[taskNumber-1].showFullDescription());
        printLine();

    }

    public static void saveToFile() throws IOException {
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));

            for (int i = 0; i < counter; i++) {
                Task currTask = listOfTasks[i];
                String formattedTask = String.format("%s | %s | %s", currTask.getTypeIcon(), currTask.getIsDone(),
                        currTask.getDescription());
                if (currTask instanceof Event) {
                    formattedTask += " | ";
                    formattedTask += ((Event) currTask).getTimePeriod();
                }
                if (currTask instanceof Deadline) {
                    formattedTask += " | ";
                    formattedTask += ((Deadline) currTask).getDueDate();
                }
                writer.write(formattedTask + "\n");
            }

        writer.close();
    }
}
