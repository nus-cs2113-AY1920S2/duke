package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Parser;
import duke.util.Storage;
import duke.exception.DukeException;

import static duke.util.Constants.DATA_FILE_NAME;

/**
 * The main entrance of the programme.
 *
 * @author A11riseforme
 */
public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Duke constructor.
     */
    public Duke()  {
        ui = new Ui();
        storage = new Storage(DATA_FILE_NAME);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Run the programme by receiving and executing users' command,
     * until receiving the exit command from user.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initializes new Duke user and runs the programme.
     *
     * @param args arguments of the programme
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
