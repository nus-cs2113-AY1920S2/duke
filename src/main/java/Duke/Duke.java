package Duke;

import Duke.Commands.Command;
import Duke.Commands.ExitCommand;
import Duke.Exception.DukeException;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Ui.Ui;

public class Duke{

    private static final String FILE_PATH = "daDuke.txt";

    public static void main(String[] args) {
        new Duke();
    }

    private Duke() {
        Ui ui = new Ui();
        ui.displayWelcomeMessage();
        Storage storage = new Storage(FILE_PATH, ui);
         while (true) {
            String userInput = ui.readCommand();
            try {
                Command command = Parser.parse(userInput);
                command.execute(ui, storage);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            }
        }
    }


}