package duke;

import duke.command.Command;
import duke.common.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static duke.common.Constants.CREATE_FILE_PATH;
import static duke.common.Constants.FILE_PATH;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
        try {
            storage.load(tasks);
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            File newFolder = new File(CREATE_FILE_PATH);
            newFolder.mkdirs();
            try{
                File f = new File(FILE_PATH);
                f.createNewFile();
            } catch (IOException error) {
                System.out.println("\tUnknown errors: " + error.getMessage());
            }
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}

