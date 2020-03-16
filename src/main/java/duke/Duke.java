package duke;

import duke.command.Command;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.constant.Constant.FILE_PATH;

/**
 * Duke is a chatbot that manages Task for user.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Public constructor for Duke.
     * @param filePath File path of the external hard disk that the Task data will be stored into.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Run the Duke program to add / view / find / delete / done Task. Terminate program when user
     * input "bye" and store all the Task data into an external hard disk.
     */
    public void run() {
        boolean isBye = false;
        ui.greetUser();
        while (!isBye) {
            try {
                String userCommand = ui.getUserCommand();
                Command command = parser.parseCommand(userCommand);
                command.execute(taskList, ui, storage);
                isBye = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method for Duke.
     * @param args String[] args in main.
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
