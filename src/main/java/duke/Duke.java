package duke;

/**
 * The Duke program is a simple chatbot that helps a user manage tasks. It
 * also saves the tasks to a text file automatically.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * This is the constructor for the Duke class that creates objects that are
     * used to run the program.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load(ui));
        } catch (DukeException e) {
            ui.printLine(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * This method runs the bulk of the program.
     */
    public void run() {
        ui.greet();
        takeCommands(ui, taskList, storage);
        ui.exit();
    }

    /**
     * This is the main method which makes use of the run method.
     * @param args Not used.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * The main loop of the program. Involves getting user input, parsing, and
     * executing commands.
     * @param ui The ui object used to interact with the user.
     * @param taskList The object being used to manipulate the tasks.
     * @param storage The object used to save tasks to disk.
     */
    private static void takeCommands(Ui ui, TaskList taskList, Storage storage) {
        String command;
        String input;
        boolean isExiting = false;
        while(!isExiting) {
            try {
                input = ui.readCommand();
                command = Parser.parse(input, taskList.getTaskList().size());
                isExiting = executeCommand(command, ui, taskList, storage);
            } catch (DukeException e) {
                ui.printFormattedString(e.getMessage());
            } catch (NumberFormatException e) {
                ui.printFormattedString(Ui.NUM_FORMAT_ERROR);
            }
        }
    }

    /**
     * Executes a given command. The tasks are saved to a file every-time
     * changes are made.
     * @param command The command executed.
     * @param ui The ui object used to interact with the user.
     * @param taskList The object being used to manipulate the tasks.
     * @param storage The object used to save tasks to disk.
     * @return Whether the program is exiting.
     */
    private static boolean executeCommand(String command, Ui ui, TaskList taskList, Storage storage) {
        String[] commandSubstrings = command.split("\\s+");
        switch (commandSubstrings[0]) {
        case "bye":
            return true;
        case "help":
            ui.helpUser();
            break;
        case "list":
            taskList.listTasks(ui);
            break;
        case "thanks":
            ui.printFormattedString(Ui.THANKS_RESPONSE);
            break;
        case "find":
            taskList.findTasks(ui, commandSubstrings[1]);
            break;
        case "done":
            taskList.checkOffTask(ui, Integer.parseInt(commandSubstrings[1]));
            storage.saveTaskstoDisk(ui, taskList);
            break;
        case "delete":
            taskList.deleteTask(ui, Integer.parseInt(commandSubstrings[1]));
            storage.saveTaskstoDisk(ui, taskList);
            break;
        case "todo":
            taskList.addTodo(command.substring(5), ui);
            storage.saveTaskstoDisk(ui, taskList);
            break;
        case "deadline":
            taskList.addDeadline(command.substring(9), ui);
            storage.saveTaskstoDisk(ui, taskList);
            break;
        case "event":
            taskList.addEvent(command.substring(6), ui);
            storage.saveTaskstoDisk(ui, taskList);
            break;
        default:
            break;
        }
        return false;
    }
}
