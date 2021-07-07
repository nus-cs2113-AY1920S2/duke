package duke;

import duke.task.*;
import duke.commands.Command;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.MissingDescriptionException;
import duke.ui.UI;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for the Duke class.
     * <p> <br>
     * Creates a new Duke object.
     * A Storage object is created to handle loading and saving of the task list.
     *</p>
     * @param filePath Path to the data directory.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks(); // Get tasks from txt file
        } catch (IOException e) {
            UI.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the entire application.
     *
     * @throws IOException If the task list fails to load.
     */
    public void run() throws IOException {
        UI.initUI();
        UI.printGreetMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = UI.readCommand();
                Command c = Parser.parse(userInput, tasks); // Prepare the Command object
                c.execute();
                storage.saveTasks(tasks); // Update txt file
                isExit = c.getExitStatus();
            } catch (InvalidCommandException e) {
                UI.showInvalidCommandError();
            } catch (MissingDescriptionException e) {
                UI.showMissingDescriptionError();
            }
        }
        UI.printEndMessage();
    }

    /**
     * Main method of the application.
     * Creates a new Duke object and runs the application.
     *
     * @param args Command line arguments. Not used.
     * @throws IOException If the task list fails to load.
     */
    public static void main(String[] args) throws IOException {
        new Duke("/data").run();
    }

}
