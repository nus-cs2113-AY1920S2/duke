import duke.commands.*;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.FileNotFoundException;

/**
 * Main chat bot class.
 */
public class Duke {
    public static Storage storage;
    public static TaskList tasks;
    public static Ui ui = new Ui();

    /**
     * Duke constructor specifying filepath to load from.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadListDataFromDisk());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DukeException e) {
            e.getMessage();
            tasks = new TaskList();
        }
    }


    public static void main(String[] args) {
        Ui.showWelcome();
        new Duke("data/duke_list.txt");

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = Ui.readInput();
                Command c = Parser.parseInput(userInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                e.getMessage();
            }
        }
    }
}