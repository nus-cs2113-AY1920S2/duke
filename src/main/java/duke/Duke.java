package duke;

import duke.commands.Command;
import duke.exceptions.ChatboxException;
import duke.exceptions.EmptyDescriptionException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

import java.io.IOException;


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
        String command = null;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                command = c.getCommand();
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatboxException e) {
                ui.displayErrorMessage(e);
            } catch (NumberFormatException e) {
                ui.displayInvalidTaskNumberMessage();
            } catch (EmptyDescriptionException e) {
                ui.displayEmptyDescriptionMessage(e.getCommand());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data", "duke.txt").run();
    }
}