package src.main.java;

import src.main.java.duke.command.Command;
import src.main.java.duke.exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Duke program implements an application that manage a task list by using specific commands.
 * User can use commands to create, mark and delete tasks inside the task list.
 * <p>
 * The program can read text file that contains a task list from specified file path.
 * Updates made to the task list by the user is written inside the same text file
 * from the specified file path or written in a new text file if no text file is found
 * in the specified file path.
 *
 * @author  Benny Chann
 * @version 1.0
 * @since   2020-03-02
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (UnknownLineFromSavedFileException e) {
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException | NumberFormatException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
