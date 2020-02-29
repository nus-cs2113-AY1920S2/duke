import java.util.Scanner;

/**
 * Represents a parser object with various methods that
 * helps Duke to parse user input for the different commands.
 */
public class Parser {

    private Ui ui = new Ui();

    public static final String[] VALID_COMMANDS =
            {"done", "list", "bye", "todo", "deadline", "event", "delete", "find"};

    /**
     * Method used to scan user input.
     * @return a string that corresponds to user input.
     */
    public String getInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    /**
     * Method used to extract the relevant command from user input.
     * @param line a string that corresponds to user input.
     * @return a string that represents the command called by user.
     * @throws InvalidCommandException if the command called by user is invalid or not defined.
     */
    public  String getCommand(String line) throws InvalidCommandException {
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

    /**
     * Method used to extract the task description from user input.
     * @param line a string that corresponds to user input.
     * @return a string that represents the task description.
     * @throws IndexOutOfBoundsException if task description could not be found.
     */
    public String getTaskInformation(String line) throws IndexOutOfBoundsException {
        int dividerPosition = line.indexOf(" ");
        if (dividerPosition == -1) {
            throw new IndexOutOfBoundsException();
        } else {
            return line.substring(dividerPosition + 1);
        }
    }

    /**
     * Method to check if the task number is provided for done and delete commands.
     * @param line a string that corresponds to user input.
     * @throws IndexOutOfBoundsException if the task number could not be found.
     */
    public String getTaskNumber(String line) throws IndexOutOfBoundsException {
        int dividerPosition = line.indexOf(" ");
        if (dividerPosition == -1) {
            throw new IndexOutOfBoundsException();
        } else {
            return line.substring(dividerPosition + 1);
        }
    }

    /**
     * Method to check if keyword is provided for find command.
     * @param line a string that corresponds to user input.
     * @throws IndexOutOfBoundsException if the keyword could not be found.
     */
    public void getKeyWord(String line) throws IndexOutOfBoundsException {
        int dividerPosition = line.indexOf(" ");
        if (dividerPosition == -1) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Method used to handle invalid commands.
     * Prints an error message to notify user of the invalid input.
     */
    public void handleInvalidCommand() {
        ui.printLine();
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        ui.printLine();
    }

    /**
     * Method used to handle tasks with no task description.
     * Prints an error message to notify user of the error.
     * @param command a string that represents the command called by user.
     */
    public void handleIndexOutOfBounds(String command) {
        ui.printLine();
        System.out.println(" ☹ OOPS!!! The description of a " + command + " cannot be empty.");
        ui.printLine();
    }

    /**
     * Method used to handle done or delete commands with invalid index.
     */
    public void handleInvalidIndex() {
        ui.printLine();
        System.out.println(" ☹ OOPS!!! The index is invalid.");
        ui.printLine();
    }

    /**
     * Method used to parse datafile and populate the task list database.
     * @param line represents a line read from the datafile.
     * @return a Task object that is interpreted from the line.
     */
    public Task readTaskFromFile(String line) {
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
        return task;
    }

    /**
     * Method used to handle invalid descriptions for deadline and event tasks.
     */
    public void handleInvalidDescription() {
        ui.printLine();
        System.out.println(" ☹ OOPS!!! The description format is invalid.");
        ui.printLine();
    }
}
