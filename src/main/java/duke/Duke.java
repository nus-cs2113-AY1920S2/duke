package duke;


import duke.exception.DukeException;
import duke.command.Command;
import java.io.FileNotFoundException;

/**
 * Duke is a command based task manager capable of handling:
 * Todos, Events and Deadlines
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for instantiation of Ui, Storage and TaskList classes.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("output.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException err) {
            ui.displayErrorMessage(err.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Starts duke and listens to incoming inputs from User.
     */
    public void run() {
        ui.startMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                ui.displayDividerLine();
                Command command = Parser.parse(userInput);
                command.execute(tasks, storage);
                isExit = command.isExit();
            } catch (DukeException err) {
                ui.displayErrorMessage(err.toString());
            } finally {
                ui.displayDividerLine();
            }
        }
    }

    /**
     * Invokes the run method.
     * @param args Not used.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
