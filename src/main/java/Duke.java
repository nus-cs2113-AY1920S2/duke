import duke.command.Command;
import duke.command.CommandOption;
import duke.command.CommandBye;
import duke.taskList.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.Storage.StorageFilePathException;
import duke.Ui.TextUi;

/**
 * The main program should keep it short
 * Exits when command "bye" stated
 *
 */
public class Duke {

    private TextUi ui;
    public Storage storage;
    private TaskList list;

    public static void main(String... launchArgs) {
        new Duke().runTheProgram(launchArgs);
    }

    public void runTheProgram(String[] launchArgs) {
        start(launchArgs);
        runCommand();
        exit();
    }

    private void start(String[] launchArgs) {
        try {
            this.ui = new TextUi();
            this.storage = initializeStorage(launchArgs);
            this.list = storage.load();
            ui.welcomeMessage(storage.getPath());
            ui.storedList();
        } catch (Storage.StorageOperationException | StorageFilePathException e) {
            ui.failed();
            throw new RuntimeException(e);
        }
    }

    private void exit(){
            ui.goodbye();
            System.exit(0);
    }

    /** run the program until the user type "bye" */
    private void runCommand(){
            Command command;
            do {
                String userCommand = ui.getUserCommand();
                command = new Parser().parseCommand(userCommand);
                CommandOption result = executeCommand(command);
                ui.printResult(result);
            } while (!CommandBye.isExit(command));
    }

    private CommandOption executeCommand(Command command) {
            try {
                command.setData(list);
                storage.save(list);
                return command.execute();
            } catch (Exception e) {
                ui.PresentMessages(e.getMessage());
                throw new RuntimeException(e);
            }

    }

    private Storage initializeStorage(String[] launchArgs) throws StorageFilePathException {
            boolean isStorageFileSpecified = launchArgs.length > 0;
            return !isStorageFileSpecified ? new Storage() : new Storage(launchArgs[0]);
    }


}
