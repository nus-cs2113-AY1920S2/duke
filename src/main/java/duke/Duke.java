package duke;

import duke.command.Command;

import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws IOException, DukeException {
        Ui ui = new Ui();
        Command command = new Command("data.txt");
        while (command.isActive) {
            command.commandInit();
            Ui.printBreak();
        }
        Ui.exit();
    }


}