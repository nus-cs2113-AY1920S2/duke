package Duke;

import Command.Command;
import Storage.Storage;
import Task.TaskList;
import UI.Ui;

import Parser.Parser;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The Duke program implements an application that simulates is a task scheduler
     * It allow the saving of tasks to a file and reloading of data when application starts
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();

        // search for folder, create if not found
        storage.checkFolderPath();

        // populate if data file is found
        if (storage.checkFileExists()) {
            storage.populateList(tasks);
        }
    }

    /**
     * Runner method to read and execute user commands
     * Loops until "bye" command given
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.setCommandVariables(tasks, storage, ui);
                c.execute();
                isExit = c.isExit();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("[Error][Input]: Missing parameters detected");
            }
            finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method for Duke program
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }


}
