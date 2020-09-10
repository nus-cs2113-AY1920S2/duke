package duke;

import duke.commands.CommandResult;
import duke.commands.ExitCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
import duke.commands.Command;

import java.util.ArrayList;

/**
 * Entry point of the Duke application
 * Initializes the application and starts the interaction with the user
 */
public class Duke {
    
    private Ui ui;
    private Storage storage;
    public static ArrayList<Task> tasks = new ArrayList<>();
    
    public static void main(String[] args) {
        new Duke().run();
    }
    
    /**
     * Set up the required object and start up the program
     */
    private void start() {
        this.ui = new Ui();
        ui.showWelcomeMessage();
        ui.showLoadMessage();
        this.storage = new Storage();
        ui.printToConsole(Ui.DIVIDER);
    }
    
    /**
     * Exit the program
     */
    private void exit() {
        ui.showGoodByeMessage();
        System.exit(0);
    }
    
    /**
     * Run the program until terminated
     */
    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }
    
    /**
     * Reads the user command and executes it, until the user issues the exit command
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        String userCommandText = "";
        do {
            userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = new Parser().executeCommand(command);
            ui.showResultToUser(result);
        } while (!userCommandText.equals(ExitCommand.COMMAND_WORD));
    }
    
}
