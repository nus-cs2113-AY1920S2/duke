import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String[] VALID_COMMANDS = {"done", "list", "bye", "todo", "deadline", "event", "delete"};
    private static ArrayList<Task> listOfTasks = new ArrayList<>();
    private static final String logo = "  ____        _        \n"
                                 + " |  _ \\ _   _| | _____ \n"
                                 + " | | | | | | | |/ / _ \\\n"
                                 + " | |_| | |_| |   <  __/\n"
                                 + " |____/ \\__,_|_|\\_\\___|\n";
    static String currDir = System.getProperty("user.dir");
    private final static String filePath = currDir + "/data.txt";

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
            return line.substring(dividerPosition + 1);
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
        try {
            loadFile();
        } catch (IOException e) {
            System.out.println("IO Exception occurred: " + e.getMessage());
        }
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
            case "delete":
                deleteTask(line);
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
        String dateAndTime = taskInformation.substring(dividerPosition + 5);
        if (command.equals("deadline")) {
            Deadline deadlineToAdd = new Deadline(description, dateAndTime);
            listOfTasks.add(deadlineToAdd);
        } else if (command.equals("event")) {
            Event eventToAdd = new Event(description, dateAndTime);
            listOfTasks.add(eventToAdd);
        }

    }

    public static void storeTaskIntoList(String taskInformation, String command) {
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
        showStoredTask();
    }

    private static void showStoredTask() {
        int sizeOfList = listOfTasks.size();
        Task lastTask = listOfTasks.get(sizeOfList - 1);

        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + lastTask);

        System.out.println(" Now you have " + sizeOfList + " tasks in the list.");
        printLine();
    }

    public static void listAllTasks() {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currTask = listOfTasks.get(i);
            System.out.println(" " + (i + 1) + ". " + currTask);

        }
        printLine();
    }

    public static void markTaskAsDone(String line) {
        int dividerPosition = line.indexOf("done");
        int taskNumber = Integer.parseInt(line.substring(dividerPosition + 5));
        Task taskDone = listOfTasks.get(taskNumber - 1);
        taskDone.markAsDone();
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskDone);
        printLine();
    }

    private static void deleteTask(String line) {
        String[] splitLine = line.split(" ");
        int taskNumber = Integer.parseInt(splitLine[1]);
        Task taskToDelete = listOfTasks.get(taskNumber - 1);
        listOfTasks.remove(taskNumber - 1);
        int numOfTasksLeft = listOfTasks.size();

        printLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + taskToDelete);
        System.out.println("Now you have " + numOfTasksLeft + " tasks in the list.");
        printLine();

    }

    public static void loadFile() throws IOException {
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String line = reader.readLine();

            while (line != null) {
                // create Tasks after loading it from the file
                String[] splitLine = line.split(", ");
                String typeIcon = splitLine[0];
                String isDoneString = splitLine[1];
                String description = splitLine[2];
                Task task;

                if (typeIcon.equals("[E]")) {
                    String timePeriod = splitLine[3];
                    task = new Event(description, timePeriod);
                } else if (typeIcon.equals("[D]")) {
                    String dueDate = splitLine[3];
                    task = new Deadline(description, dueDate);
                } else {
                    task = new Todo(description);
                }
                boolean isDone = Boolean.parseBoolean(isDoneString);
                task.setIsDone(isDone);
                listOfTasks.add(task);

                line = reader.readLine();
            }
        }
    }

    public static void saveToFile() throws IOException {
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));

        // format task before writing it to file
        for (Task currTask : listOfTasks) {
            String formattedTask = String.format("%s, %s, %s", currTask.getTypeIcon(), currTask.getIsDone(),
                    currTask.getDescription());
            if (currTask instanceof Event) {
                formattedTask += ", ";
                formattedTask += ((Event) currTask).getTimePeriod();
            }
            if (currTask instanceof Deadline) {
                formattedTask += ", ";
                formattedTask += ((Deadline) currTask).getDueDate();
            }
            writer.write(formattedTask + "\n");
        }

        writer.close();
    }
}
