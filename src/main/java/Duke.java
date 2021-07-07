import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

import static duke.constants.Messages.*;

/**
 * Welcome to the Duke, your Task Manager that can help you
 * with storing tasks as a todo, deadline, or event.
 *
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * This method initializes variables.
     * @param filename The name of the file
     */
    public Duke (String filename) {
        ui = new Ui();
        storage = new Storage(filename);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This method runs the program by scanning in user
     * input. It looks out for keywords to add, delete, mark as
     * done, list or find tasks before it exits
     */

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command command = parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(MESSAGE_ENTER_TASK);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                System.out.println(MESSAGE_REENTER_DATE);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println(MESSAGE_REENTER_TASK_NUMBER);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println(MESSAGE_NO_TASK_NUMBER_EXISTS);
            }
        }
    }

    /**
     * This is the main method of the program
     * @param args The main argument of this program
     */
    public static void main(String[] args) {
        try {
            new File(DUKE_FILE_PATH).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Duke(DUKE_FILE_PATH).run();
    }

}
