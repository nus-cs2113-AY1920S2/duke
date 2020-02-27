package Duke;

import Command.Command;
import Exceptions.DukeException;
import Storage.Storage;
import Task.TaskList;
import UI.Ui;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Parser.Parser;

    /*
    List of exceptions handled:

    1. General commands
        a. No recognised command given
        b. No follow up parameters in command

    2. Done command
        a. Out of range
        b. Not integer

    3. Load from data.txt
        a. Not exist
        b. Error reading / writing

     */


public class Duke {

    public static void getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
            } catch (Exception e) {
                System.out.println("ERROR AT RUN\n");
                ui.showError(e.getMessage());
                System.out.println(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
