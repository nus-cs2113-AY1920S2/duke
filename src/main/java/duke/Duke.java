package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class Duke {
    
    private Ui ui;
    
    public static ArrayList<Task> tasks = new ArrayList<>();
    
    public static void main(String[] args) {
        new Duke().run();
    }
    
    private void start() {
        this.ui = new Ui();
        ui.showWelcomeMessage();
        new Storage();
    }
    
    private void exit() {
        ui.showGoodByeMessage();
        System.exit(0);
    }
    
    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }
    
    private void runCommandLoopUntilExitCommand() {
        String userCommandText = "";
        do {
            userCommandText = ui.getUserCommand();
            new Parser().executeCommand(userCommandText);
        } while (!userCommandText.equals(Parser.COMMAND_BYE_WORD));
    }
}
