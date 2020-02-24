package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    public static final String FILE_PATH = "TaskList.txt";
    public static final String BYE_COMMAND = "bye";

    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private static TaskList taskList;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        parser = new Parser();
        taskList = new TaskList(storage.loadData());
    }

    public void run() {
        ui.printWelcomeMessage();
        parseAndExecuteCommands();
        storage.storeTasksToFile(taskList);
        ui.printByeMessage();
    }

    private void parseAndExecuteCommands() {
        Scanner sc = new Scanner(System.in);
        String fullCommand;
        Command command;

        fullCommand = sc.nextLine();
        while (!fullCommand.equals(BYE_COMMAND)) {
            try {
                command = parser.parseCommand(fullCommand);
                command.executeCommand(taskList);
            } catch (DukeException dukeException) {
                dukeException.printExceptionMessage();
            }
            ui.printEmptyLine();
            fullCommand = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

}
