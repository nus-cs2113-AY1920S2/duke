import Exceptions.MissingDescriptionException;
import Exceptions.UnknownCommandException;

import java.io.FileNotFoundException;

public class Jan {
    public static final String FILEPATH = "data/TaskList.txt"; //final save

    private static Ui ui;
    private static StorageManager storageManager;
    private static TaskManager taskManager;

    /**
     * Initialise task list and variables needed to run the program
     */
    public static void init(){
        ui = new Ui();
        storageManager = new StorageManager(FILEPATH);

        try {
            taskManager = new TaskManager(storageManager.load());
        } catch (FileNotFoundException e) {
            taskManager = new TaskManager();
        }
    }

    public static void main(String[] args) {
        init();
        ui.printGreetingMessage();

        while (true) {
            String fullCommand = ui.readCommand();
            ui.printDivider();

            if(fullCommand.equals("bye")) {
                break;
            }

            try {
                Command command = Parser.parse(fullCommand);
                command.execute(ui, taskManager, storageManager);
            }catch (MissingDescriptionException e) {
                ui.printIncorrectFormatMessage();
            } catch (UnknownCommandException e) {
                ui.printHelpMessage();
            } finally {
                ui.printDivider();
            }
        }

        ui.printGoodbyeMessage();
        ui.printDivider();
    }
}
