import exception.DukeException;
import task.TaskList;
import storage.Storage;
import ui.Ui;
import commands.Command;
import parser.Parser;

/**
 * Entry point of the Duke application.
 * Initializes the application and interacts with the user
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the user interface and loads data into the application
     * @param dataPath path of the data file
     */
    private Duke(String dataPath) {
        ui = new Ui();
        storage = new Storage(dataPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Reads the user's commands and executes them until the user issues the exit command.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Starts the application
     * @param args
     */
    public static void main(String[] args) {
        new Duke("Duke.txt").run();
    }
}
