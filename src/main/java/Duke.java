import Commands.Command;
import Exceptions.DukeException;

import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printHardDiskNotFound();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String rawUserInput = ui.readCommand();
                ui.printDivider();
                Command command = Parser.parse(rawUserInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printErrorMessage(e.toString());
            } finally {
                ui.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
