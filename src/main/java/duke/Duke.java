package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Duke class - the main class for Duke project
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor of the main class Duke
     * @see Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method to run a Duke program
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // show the divider line ("_______")
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                ui.showNextLine();
            }
        }
    }

    /**
     * Main function of the Duke program
     *
     * @param args main's arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
