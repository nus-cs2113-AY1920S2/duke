
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.Exception.DukeException;
import duke.parser.Parser;

/**
 * Represents an command-line organization program that keeps track of tasks that either the user inputs
 * or have been loaded from a previous text file. The user is able to keep track of tasks, add, delete, search,
 * and mark some as completed by typing the appropriate command into the command line.
 */
public class Duke {

    private static TaskList taskLists;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;

    public Duke() {
        taskLists = new TaskList();
        ui = new Ui(taskLists);
        storage = new Storage(taskLists);
        parser = new Parser(taskLists);
        try {
            taskLists = new TaskList(storage.load());
        } catch (DukeException e ) {
            ui.showLoadingError();
            taskLists = new TaskList();
        }
    }

    public void run() {
        ui.greeting();
        while (true) {
            String response = parser.getUserResponse();
            parser.doUserCommand(response);
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }



}


