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
     * @param filepath relative filepath to store the data.txt file
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
            switch (command) {
            case "bye":
                ui.exitFromApp();
                break;
            case "list":
                tasks.listAllTasks();
                break;
            case "done":
                tasks.markTaskAsDone(line);
                try {
                    storage.saveToFile(tasks);
                } catch (IOException e) {
                    ui.showLoadingError();
                }
                break;
            case "delete":
                tasks.deleteTask(line);
                try {
                    storage.saveToFile(tasks);
                } catch (IOException e) {
                    ui.showLoadingError();
                }
                break;
            case "find":
                tasks.findTask(line);
                break;
            default:
                String taskInformation;
                try {
                    taskInformation = parser.getTaskInformation(line);
                } catch (IndexOutOfBoundsException b) {
                    parser.handleIndexOutOfBounds(command);
                    continue;
                }
                tasks.storeTaskIntoList(taskInformation, command);
                try {
                    storage.saveToFile(tasks);
                } catch (IOException e) {
                    ui.showLoadingError();
                }
                break;
            }
        }
    }

    /**
     * Point of entry for the Duke program.
     * @param args unused
     */
    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
