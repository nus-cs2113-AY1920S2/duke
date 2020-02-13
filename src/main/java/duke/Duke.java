package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.NoDateException;
import duke.exception.NoDescException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;  // User input
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

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



        // if it does, load
        // duke.csv file format:
        // taskID, taskType (e.g. deadline/event), taskIsDone, taskDesc, taskDate

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
        String dataLine = t.toData(taskId);

        // Write to data file
        try {
            writeToFile(dataLine);
        } catch (IOException e) {
            formatPrint("Error saving task to data file.");
        }
        formatPrint("Added task: " + t);
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
            String taskId = userParams.replaceAll("[^0-9]", ""); // Extract numeric characters
            taskList.get(Integer.parseInt(taskId) - 1).markAsDone(); // Mark task with that ID as done
            formatPrint("Marked task as done.");
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
