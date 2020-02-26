package duke;

import duke.manager.Manager;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TextUi ui;
    private TaskList tasks;

    /**
     * Sets up the required objects, loads up the data from the storage file.
     */
    public Duke() {
        ui = new TextUi();
        storage = new Storage();
        try {
            tasks = new TaskList();
            storage.retrieve(tasks);
        } catch (FileNotFoundException e) {
            TextUi.showLoadingError();
        }
    }

    /** Runs the program until termination.  */
    public void run() {
        ui.printWelcomeMessage();
        String name = ui.InputUserName();
        Scanner sc = new Scanner(System.in);

        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            String[] parseInput = Parser.parser(fullCommand);
            String command = Parser.getCommand(parseInput);
            Manager.manager(parseInput, name, tasks, command);
            isExit = Manager.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}