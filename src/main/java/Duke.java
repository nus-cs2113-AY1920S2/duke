import task.*;
import commands.Command;

import exceptions.InvalidCommandException;
import exceptions.MissingDescriptionException;
import ui.UI;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.loadTasks(); // Get tasks from txt file
        } catch (IOException e) {
            UI.showLoadingError();
            tasks = new TaskList();
        }
    }

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

    public static void main(String[] args) throws IOException {
        new Duke("/data").run();
    }

}
