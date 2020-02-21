package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exception.FormatErrorException;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidInputException;
import duke.exception.OutOfBoundsException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;
import java.io.IOException;
import static duke.common.Messages.WELCOME_MESSAGE;
import static duke.common.Messages.COMMAND_FAIL;
import static duke.common.Messages.INVALID_MESSAGE;
import static duke.common.Messages.INCOMPLETE_MESSAGE;
import static duke.common.Messages.INDEX_PROBLEM_MESSAGE;
import static duke.common.Messages.FILE_ERROR_MESSAGE;
import static duke.common.Messages.FORMAT_PROBLEM_MESSAGE;

/**
 * Entry point of the Duke application.
 * Manages interactions between storage, UI and the list of tasks.
 */
public class Duke {

    /** Storage object that loads tasks from file and saves tasks to file */
    private static Storage storage;

    /** List of tasks */
    private static TaskList tasks;

    /** UI that interacts with the user */
    private static Ui ui;

    /** Flag that indicates whether an exit command has been encountered */
    private static boolean isExit;

    /**
     * Runs the application.
     *
     * @param args Not significant in this case.
     */
    public static void main(String[] args) {
        initialiseDuke();
        try {
            storage.initialiseList(tasks);
        } catch (IOException e) {
            ui.showMessage(FILE_ERROR_MESSAGE);
        } catch (InvalidInputException e) {
            ui.showMessage(INVALID_MESSAGE);
        }
        ui.showMessage(WELCOME_MESSAGE);
        while (!isExit) {
            try {
                String command = ui.readInput();
                Command newCommand = Parser.parseCommand(command,tasks);
                CommandResult result = newCommand.execute(tasks,ui,storage);
                ui.showResult(result);
                isExit = newCommand.isExit;
            } catch (InvalidInputException e) {
                ui.showMessage(INVALID_MESSAGE);
            } catch (IncompleteInputException e) {
                ui.showMessage(INCOMPLETE_MESSAGE);
            } catch (NumberFormatException e) {
                ui.showMessage(INDEX_PROBLEM_MESSAGE);
            } catch (OutOfBoundsException e) {
                ui.showMessage(INDEX_PROBLEM_MESSAGE);
            } catch (FormatErrorException e) {
                ui.showMessage(FORMAT_PROBLEM_MESSAGE);
            } catch (InvalidCommandException e) {
                ui.showMessage(COMMAND_FAIL);
            }
        }
    }

    /**
     * Initialises the application, including all required resources.
     */
    private static void initialiseDuke() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage();
        isExit = false;
    }


}
