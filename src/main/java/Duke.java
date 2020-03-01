
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.Exception.DukeException;
import duke.parser.Parser;

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
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e ) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
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


