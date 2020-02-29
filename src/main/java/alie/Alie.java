package alie;

import alie.commands.Command;
import alie.commands.CommandResult;
import alie.commands.ExitCommand;
import alie.exceptions.InvalidFileFormatException;

import java.io.FileNotFoundException;

/*
@@author e0309556-reused
Adapted logic for refactoring of Storage, Ui from https://github.com/nus-cs2113-AY1920S2/personbook
with minor modification in the public class Alie.
*/
public class Alie {
    private Storage storage;
    private TaskManager taskList;
    private Ui ui;

    /**
     * Construct ALIE object by instantiating Ui, Storage and TaskManager classes
     * then loads saved data if any.
     * @param filePath Desired file path for where saved data is kept at
     */
    public Alie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskManager(storage.readFromFile(ui));
        } catch (FileNotFoundException | InvalidFileFormatException errorMsg) {
            ui.print(Ui.FILE_NOT_FOUND_ERROR);
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
     * Starts up ALIE with welcome message.
     */
    private void start() {
        ui.print(Ui.WELCOME_MSG);
    }

    /**
     * Main logic of app; Repeatedly read input from user and execute ALIE
     * commands until "bye" input is detected.
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
            } catch (Exception errorMsg) {
                ui.print(errorMsg.toString());
            } finally {
                ui.print(ui.DIVIDER);
            }
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the command provided by user input
     * @param command Command object provided by user to be executed
     * @return CommandResult object containing result from executing command
     * @throws Exception Any exceptions that could be thrown while executing all the
     * different command
     */
    public CommandResult executeCommand(Command command) throws Exception {
        return command.execute(taskList, ui, storage);
    }

    /**
     * Exits app after showing goodbye message
     */
    private void exit() {
        ui.print(Ui.GOODBYE_MSG);
        System.exit(0);
    }
}
