package duke;

import duke.commands.Command;
import duke.exceptions.*;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * The Duke program implements an interactive chatbox that
 * allows the user to record and update their tasks.
 * 
 * @author Wu Sibing
 * @version 1.0
 * @since 2020-02-29
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Generates the ui interaction, sets up the txt file that saves the task list, and creates the task list.
     * 
     * @param dirPath Path to the directory where the txt file locates.
     * @param fileName Name of the txt file.
     */
    public Duke(String dirPath, String fileName) {
        ui = new Ui();
        ui.displayWelcomeMessage();
        storage = new Storage(dirPath, fileName);
        try {
            tasks = new TaskList(storage.loadFileContents());
        } catch (IOException e) {
            ui.displayLoadingError();
            tasks = new TaskList();
        } catch (ChatboxException e) {
            ui.displayEmptyListMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Executes the main body of the program.
     * Reads in commands, executes the command, updates the task list and txt file accordingly.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NumberFormatException e) {
                ui.displayInvalidTaskNumberMessage();
            } catch (CommandNotFoundException e) {
                ui.displayCommandNotFoundMessage();
            } catch (EmptyDescriptionException e) {
                ui.displayEmptyDescriptionMessage(e.getCommand());
            } catch (MarkerMissingException e) {
                ui.displayMarkerMissingMessage(e);
            } catch (TimeMissingException e) {
                ui.displayTimeMissingMessage();
            } catch (InvalidTaskNumberException e) {
                ui.displayInvalidTaskNumberMessage();
            } catch (ChatboxException e) {
                ui.displayErrorMessage(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.displayErrorMessage(e);
            } catch (DateTimeException e) {
                ui.displayTimeFormatErrorMessage();
            }
        }
    }

    /**
     * Runs the program and specifies where the txt file locates.
     * 
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }
}