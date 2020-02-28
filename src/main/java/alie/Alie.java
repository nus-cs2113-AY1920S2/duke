package alie;

import alie.commands.Command;
import alie.commands.CommandResult;
import alie.commands.ExitCommand;
import alie.exceptions.InvalidFileFormatException;

import java.io.FileNotFoundException;


public class Alie {
    private Storage storage;
    private TaskManager taskList;
    private Ui ui;

    /**
     * Construct ALIE object by instantiating Ui, Storage and TaskManager classes
     * then loads saved data if any.
     * @param filePath Desired filepath for where saved data is kept at
     */
    public Alie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskManager(storage.readFromFile());
        } catch (FileNotFoundException | InvalidFileFormatException errorMsg) {
            ui.showLoadingError();
            taskList = new TaskManager();
        }
    }

    /**
     * The main entry point to run Duke.
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Alie(Storage.findStoragePath()).run();
    }

    /**
     * Runs ALIE.
     */
    public void run() {
        start();
        runCmdLoopTillExitCmd();
        exit();
    }

    /**
     * Starts up ALIE with default welcome message.
     */
    private void start() {
        ui.showWelcome();
    }

    /**
     * Main logic of app; Repeatedly read input from user and execute ALIE
     * commands until "bye" input is entered.
     */
    private void runCmdLoopTillExitCmd() {
        Command command = null;
        do {
            try {
                String userCommandText = ui.getUserCommand();
                command = new Parser().parseCommand(userCommandText);
                CommandResult result = executeCommand(command);
                ui.showCmdResult(result);
                storage.save(taskList);
            } catch (Exception e) {
                ui.showError(e);
            }
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the command provided as input by user
     * @param command Command provided by user to be executed
     * @return Result from executing command
     * @throws Exception Any exceptions that could be thrown while executing command
     */
    public CommandResult executeCommand(Command command) throws Exception {
        return command.execute(taskList, ui, storage);
    }

    /**
     * Exits app after showing goodbye message
     */
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}
