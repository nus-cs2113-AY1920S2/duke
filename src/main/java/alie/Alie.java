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

    public static void main(String[] args) {
        new Alie(Storage.findStoragePath()).run();
    }

    public void run() {
        start();
        runCmdLoopTillExitCmd();
        exit();
    }

    private void start() {
        ui.showWelcome();
    }

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

    public CommandResult executeCommand(Command command) throws Exception {
        return command.execute(taskList, ui, storage);
    }

    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}
