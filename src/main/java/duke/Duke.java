package duke;


import duke.exception.DukeException;
import duke.command.Command;
import java.io.FileNotFoundException;


public class Duke {

    public static final String END_COMMAND = "bye";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage("output.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException err) {
            ui.displayErrorMessage(err.toString());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.startMessage();
        while (true) {
            try {
                String userInput = ui.readCommand();
                if (userInput.equals(END_COMMAND)) {
                    ui.endMessage();
                    break;
                } else {
                    ui.displayDividerLine();
                    Command command = Parser.parse(userInput);
                    command.execute(tasks, storage);
                }
            } catch (DukeException err) {
                ui.displayErrorMessage(err.toString());
            } finally {
                ui.displayDividerLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }

}
