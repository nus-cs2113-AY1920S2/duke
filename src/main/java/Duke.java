import java.io.IOException;

/**
 * Implements a chat bot named Duke that helps the user keep track
 * of various things with its different functions.
 */
public class Duke {

    static String currDir = System.getProperty("user.dir");
    private final static String filePath = currDir + "/data.txt";

    private Storage storage;
    private TaskList tasks;
    private static Ui ui;
    private Parser parser;

    /**
     * Represents a constructor for the Duke Object. The constructor
     * helps to initialise other object classes that is useful to the
     * execution of the program.
     * @param filepath relative filepath to store the data.txt file.
     */
    public Duke(String filepath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Waits for user input to determine the command to execute.
     * Terminates the program only when the command 'bye' is received.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String line = parser.getInput();
            String command;
            try {
                command = parser.getCommand(line);
            } catch (InvalidCommandException c) {
                parser.handleInvalidCommand();
                continue;
            }
            runCommand(line, command);
        }
    }

    /**
     * Method to execute the right command from the available list of commands.
     * @param line a string that provides relevant information to the execution of the command.
     * @param command a string that represents the command requested by the user.
     */
    private void runCommand(String line, String command) {
        switch (command) {
        case "bye":
            ui.exitFromApp();
            break;
        case "list":
            tasks.listAllTasks();
            break;
        case "done":
            handleDoneCommand(line);
            break;
        case "delete":
            handleDeleteCommand(line);
            break;
        case "find":
            tasks.findTask(line);
            break;
        default:
            handleAddCommands(line, command);
            break;
        }
    }

    /**
     * Method to handle commands that involves adding tasks to the task list.
     * @param line a string that describes the task and its information.
     * @param command a string that represents the type of the task.
     */
    private void handleAddCommands(String line, String command) {
        String taskInformation;
        try {
            taskInformation = parser.getTaskInformation(line);
        } catch (IndexOutOfBoundsException b) {
            parser.handleIndexOutOfBounds(command);
            return;
        }
        tasks.storeTaskIntoList(taskInformation, command);
        try {
            storage.saveToFile(tasks);
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Method to handle deleting tasks from the task list.
     * @param line a string that gives information on the task to delete.
     */
    private void handleDeleteCommand(String line) {
        tasks.deleteTask(line);
        try {
            storage.saveToFile(tasks);
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Method to set a task as done in the task list.
     * @param line a string that gives information on the task done.
     */
    private void handleDoneCommand(String line) {
        tasks.markTaskAsDone(line);
        try {
            storage.saveToFile(tasks);
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Point of entry for the Duke program.
     * @param args unused.
     */
    public static void main(String[] args) {
        new Duke(filePath).run();
    }


}
