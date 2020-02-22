package duke;

import duke.Util.*;

import java.io.IOException;

public class Duke {
    private UI ui;
    private Parser parser;
    public Duke() {
        ui = new UI();
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.run();
    }
    public void run() throws IOException {
        ui.printIntro();
        ui.printExeType();
        String exeCommand = ui.getStringInput();
        while (!exeCommand.equals("6")) {
            parser = new Parser(ui);
            parser.parseCommand(exeCommand); //to select the exeType and execute it
            ui.printExeType(); //user guide after execution of command
            exeCommand = ui.getStringInput(); //get the next command
        }
        ui.printExit();
    }
}
