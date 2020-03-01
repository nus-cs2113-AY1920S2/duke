import exception.DukeException;
import task.TaskList;
import storage.Storage;
import ui.Ui;
import commands.Command;
import parser.Parser;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String dataPath) {
        ui = new Ui();
        storage = new Storage(dataPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void run() {
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
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
