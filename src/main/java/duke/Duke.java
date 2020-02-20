package duke;

import duke.command.Command;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Entry point of Duke application.
 * Initializes the application and starts the interaction with the user.
 */

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /** Runs the application until user enters "bye". */
    public void run() {
        start();
        processCommand();
        exit();
    }

    /** Starts with greeting to the user. */
    public void start() {
        ui.greet();
    }

    /** Exits with saying "bye" to the user. */
    public void exit() {
        ui.bye();
    }

    /** Reads the user command and execute it until user enters "bye". */
    public void processCommand() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }

        }
    }


}


