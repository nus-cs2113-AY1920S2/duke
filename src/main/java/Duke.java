import Commands.Command;
import Exceptions.DukeException;
import Exceptions.HardDiskCorruptedException;
import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

import java.io.FileNotFoundException;

/**
 * Class representing a command line task manager
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs Duke object and instantiate Ui, Storage and TaskList objects
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printHardDiskNotFound();
            taskList = new TaskList();
        } catch (HardDiskCorruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * Starts the Duke process
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String rawUserInput = ui.readCommand();
                ui.printDivider();
                Command command = Parser.parse(rawUserInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printErrorMessage(e.toString());
            } finally {
                ui.printDivider();
            }
        }
    }

    /**
     * Creates Duke object and runs the process
     * @param args unused arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
