package duke;

import duke.commands.Command;
import duke.common.Messages;
import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TextUi ui;
    private TaskList tasks;

    /**
     * Sets up the required objects, loads up the data from the storage file.
     */
    public Duke() {
        try {
            ui = new TextUi();
            storage = new Storage();
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the entire application and stores the task list before exit by calling relevant functions.
     * Catch any exception thrown in the following functions.
     *
     * @see #TextUi#showWelcome()
     * @see #TextUi#readCommand()
     * @see #Parser#paser()
     * @see #Command#execute()
     * @see Ui#printByeMessage()
     */

    /**
     * Runs the program until termination.
     */
    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        try {
            while (!isExit) {
                try {
                    String fullCommand = ui.readCommand();
                    System.out.println(Messages.DIVIDER);
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks);
                    isExit = c.isExit();
                } catch (DukeException de) {
                    System.out.println(de.getMessage());
                } catch (NumberFormatException nfe) {
                    System.out.println(Messages.MESSAGE_INDEX_NOT_AN_INTEGER);
                } catch (Exception e) {
                    System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } finally {
                    ui.showLine();
                }
            }
            storage.save(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
