package duke;

import duke.commands.Command;
import duke.exceptions.ChatboxException;
import duke.exceptions.CommandNotFoundException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.TimeMissingException;
import duke.exceptions.InvalidTaskNumberException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.zip.DataFormatException;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }
}