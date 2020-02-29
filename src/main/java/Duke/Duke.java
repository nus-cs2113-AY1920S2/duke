package Duke;

import Duke.Commands.Command;
import Duke.Commands.ExitCommand;
import Duke.Exception.DukeDateParseException;
import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.Ui.Ui;

import static Duke.Task.TaskList.executeCommand;

public class Duke{

    private static final String FILE_PATH = "Duke.txt";

    public static void main(String[] args) {
        new Duke();
    }

    private Duke() {
        Ui ui = new Ui();
        Ui.displayWelcomeMessage();
        Storage storage = new Storage(FILE_PATH, ui);
         while (true) {
            String userInput = ui.readCommand();
            try {
                Command command = executeCommand(userInput);
                command.execute(ui, storage);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException e) {
                Ui.displayError(e.getMessage());
            }
        }
    }


}