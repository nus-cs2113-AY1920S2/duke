package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.NoDateException;
import duke.exception.NoDescException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;  // User input
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.stream.Stream;

public class Duke {

    static String dataFilePath = "resources/data.csv";
    static File dataFile = new File(dataFilePath);
    static List<Task> taskList = new ArrayList<Task>();

    private static void intro()
    {
        loadFile();
        // Logo generated using http://patorjk.com/software/taag/#p=display&f=Fire%20Font-s&t=NUSBOT
        String logo = "    )       (           )          \n"
                + " ( /(       )\\ )  (  ( /(   *   )  \n"
                + " )\\())   ( (()/(( )\\ )\\())` )  /(  \n"
                + "((_)\\    )\\ /(_))((_|(_)\\  ( )(_)) \n"
                + " _((_)_ ((_|_))((_)_  ((_)(_(_())  \n"
                + "| \\| | | | / __|| _ )/ _ \\|_   _|  \n"
                + "| .` | |_| \\__ \\| _ \\ (_) | | |    \n"
                + "|_|\\_|\\___/|___/|___/\\___/  |_|    \n"
                + "                                   \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Type 'bye' to leave at any time.");
    }

    private static void loadFile() {

        // Create data file if it does not exist already
        if (!dataFile.exists()) {
            try  {
                dataFile.createNewFile();
            } catch (IOException e) {
                formatPrint("Error loading data file.");
            }
            return;
        }

        try {
            Scanner dataScanner = new Scanner(dataFile);
            while (dataScanner.hasNext()) {
                parseDataLine(dataScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            formatPrint("Error: file not found.");
        }
    }

    private static void parseDataLine(String s) {
        List<String> strings = Arrays.asList(s.split(","));

        boolean isDone = Boolean.parseBoolean(strings.get(2));

        switch (strings.get(1)) {
        case "T":
            Todo t = new Todo(isDone, strings.get(3));
            taskList.add(t);
            break;
        case "D":
            Deadline d = new Deadline(isDone, strings.get(3), strings.get(4));
            taskList.add(d);
            break;
        case "E":
            Event e = new Event(isDone, strings.get(3), strings.get(4));
            taskList.add(e);
            break;
        }
    }

    private static void writeToFile(String s) throws IOException {
        FileWriter fw = new FileWriter(dataFilePath, true);
        fw.write(s + System.lineSeparator());
        fw.close();
    }

    private static void addTask(Task t) {
        taskList.add(t); // Add to running taskList

        int taskId = taskList.size()-1; // Get ID of task in running taskList

        // Convert to comma-separated information
        // duke.csv file format:
        // taskID, taskType, taskIsDone, taskDesc, taskDate
        String dataLine = t.toData(taskId);

        // Write to data file
        try {
            writeToFile(dataLine);
        } catch (IOException e) {
            formatPrint("Error saving task to data file.");
        }
        formatPrint("Added task: " + t);
    }

    private static void taskDone(int taskId) {
        taskList.get(taskId).markAsDone(); // Mark task with that ID as done
        formatPrint("Marked task as done.");

        try {
            replaceLine(taskId, taskList.get(taskId).toData(taskId));
        } catch (IOException e) {
            formatPrint("Error updating line in data file.");
        }
    }

    private static void replaceLine(int lineNumber, String newString) throws IOException {
        // Read file into list of strings, where each string is a line in the file
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(dataFilePath), StandardCharsets.UTF_8));

        // Iterate through the lines
        for (int i = 0; i < fileContent.size(); i++) {
            // If the current line matches the taskId
            if (fileContent.get(i).startsWith(String.valueOf(lineNumber))) {
                // Replace it with the new task string
                fileContent.set(i, newString);
                break;
            }
        }
            Files.write(Paths.get(dataFilePath), fileContent, StandardCharsets.UTF_8);
    }

    private static void formatPrint(String input) {
        System.out.println("----------");
        System.out.println(input);
        System.out.println("----------");
    }

    private static void printList() {
        System.out.println("----------");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + ". " + taskList.get(i));
        }
        System.out.println("----------");
    }

    private static void parseCommand(String userCommand, String userParams) throws NoDescException, NoDateException, InvalidDateFormatException, InvalidCommandException {
        switch (userCommand) {
        case "todo":
            // Check that description exists
            if (userParams.trim().isEmpty()) {
                throw new NoDescException();
            }
            addTask(new Todo(userParams.trim()));
            break;
        case "deadline":
            // Fallthrough
        case "event":
            int delimIndex = userParams.indexOf("/"); // duke.Duke uses / to define where the date starts

            // If String.indexOf returns -1, the character has not been found
            if (delimIndex == -1) {
                throw new InvalidDateFormatException();
            }

            String desc = userParams.substring(0, delimIndex); // Get description substring (before /)
            String date = userParams.substring(delimIndex+1, userParams.length()); // Get date substring (after /)

            // Check that description and date exist
            if (desc.trim().isEmpty()) {
                throw new NoDescException();
            } else if (date.trim().isEmpty()) {
                    throw new NoDateException();
                }
            if (userCommand.equals("deadline")) {
                addTask(new Deadline(desc.trim(), date.trim()));
            } else {
                addTask(new Event(desc.trim(), date.trim()));
            }
            break;
        case "done":
            String stringId = userParams.replaceAll("[^0-9]", ""); // Extract numeric characters
            int taskId = Integer.parseInt(stringId) - 1;
            taskDone(taskId);
            break;
        case "delete":
            int idTaskDelete;
            String taskToRemove = userParams.replaceAll("[^0-9]", ""); // Extract numeric characters
            taskList.remove(Integer.parseInt(taskToRemove) - 1);
            formatPrint("Deleted task: " + taskList.get(Integer.parseInt(taskToRemove) - 1));
                    System.out.println(i+1 + ". " + taskList.get(i));
            break;
        case "list":
            printList();
            break;
        default:
            throw new InvalidCommandException();
            // Note: break statement not needed here because the exception is thrown by default, which stops flow
        }
    }

    public static void main(String[] args) {
        intro();

        // Prepare for first input
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What can I do for you?");

        // Note: Scanner.next() reads until delimiter, Scanner.nextLine() reads until EOL
        // If Scanner.next() is called first, then Scanner.nextline() reads from that point onwards
        // e.g. if the user inputs: 'deadline read book \5pm', the two strings below will contain:
        //      userCommand = 'deadline'
        //      userParams  = 'read book \5pm'
        String userCommand = inputScanner.next(); // Read first word of input
        String userParams = inputScanner.nextLine(); // Get user input after first word

        // Main execution loop
        while(!userCommand.equalsIgnoreCase("bye")) {
            try {
                parseCommand(userCommand, userParams);
            } catch (NoDescException e) {
                formatPrint("Please input a description.");
            } catch (NoDateException e) {
                formatPrint("Please input a date.");
            } catch (InvalidDateFormatException e) {
                formatPrint("Invalid input method. Please input the task in the following format: ");
                switch (userCommand) {
                case "deadline":
                    formatPrint("deadline description /date");
                    break;
                case "event":
                    formatPrint("event description /date");
                    break;
                }
            } catch (InvalidCommandException e) {
                formatPrint("Sorry, I didn't recognize that command.");
            }

            System.out.println("You have " + taskList.size() + " task/s. (type 'list' to list your tasks)");
            System.out.println("Anything else? Remember that you can leave by typing 'bye'.");

            userCommand = inputScanner.next(); // Prepare for next user command
            userParams = inputScanner.nextLine(); // Get user input after first word
        }
        System.out.println("Goodbye!");
    }
}
