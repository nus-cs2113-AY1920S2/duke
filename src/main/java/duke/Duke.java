package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean fileHasChanges = false;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandLine = ui.parseCommand();
                Command c = Parser.parse(commandLine);
                c.execute(tasks, ui);
                if(!(c instanceof HelpCommand || c instanceof ListCommand || c instanceof ExitCommand)) {
                    fileHasChanges = true;
                }
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showLoadingError(e.getMessage());
            }
        }
        if (fileHasChanges) {
            try {
                storage.save(tasks.getTaskList());
            } catch (DukeException e) {
                ui.showLoadingError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
