package duke;

import duke.commands.ExecuteCommand;
import duke.exceptions.*;
import java.io.FileNotFoundException;

public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage("output.txt");
        try {
            tasks = new TaskList(storage.getData());
        } catch (FileNotFoundException err) {
            ui.errorMessage(err.toString());
            tasks = new TaskList();
        }
    }

    public void startDuke() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userData = ui.readUserData();
                ExecuteCommand command = Parser.parse(userData);
                command.execute(tasks, storage);
                isExit = command.toExit();
            } catch (DukeException err) {
                ui.errorMessage(err.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().startDuke();
    }
}