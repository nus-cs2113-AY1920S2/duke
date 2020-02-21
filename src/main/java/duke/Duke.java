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

public class Duke {

    private static Storage storage;

    private static TaskList tasks;

    private static Ui ui;

    private static boolean isExit;

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

    private static void initialiseDuke() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage();
        isExit = false;
    }


}
