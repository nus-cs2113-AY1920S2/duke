import java.io.FileNotFoundException;
import java.io.IOException;
import common.Messages;
import data.*;
import tasklist.TaskList;

import static common.Messages.SAVE_TASKLIST_TO_FILE_FAILURE_MESSAGE;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Messages messageContainer = new Messages();

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadFileToTaskList());
        } catch (FileNotFoundException e) {
            //ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke main = new Duke("data/duke.txt");
        main.runStartup();
        main.runLoopUntilExit();
        main.runExit();
    }

    public void runStartup() {
        ui.sayIntro();
    }

    public void runLoopUntilExit() {
        Parser commandParser = new Parser(tasks);
        while (commandParser.exitCommandNotEncountered()) {
            try {
                String userInputText = ui.getUserCommand();
                /*processedCommand = */
                Command nextCommand = commandParser.parseCommand(userInputText);
                nextCommand.execute(tasks, ui);
            } catch (NoRemarkException
                    | IllegalKeywordException
                    | NoDescriptionException
                    | NumberFieldException
                    | MissingParameterException e) {
                ui.displayMessage(e.getMessage());
            }
        }
    }

    public void runExit() {
        //TODO: save tasklist to storage
        try {
            storage.saveTaskListToFile(tasks);
        } catch (IOException e) {
            ui.displayMessage(SAVE_TASKLIST_TO_FILE_FAILURE_MESSAGE);
        }

        ui.sayGoodbye();
        System.exit(0);
    }

}
