package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Duke class - the main class for Duke project
 */
public class Duke {
    public static final int TASK_LIMIT = 100;
    public static final String TAB_SPACE = "\t";
    public static final String DIVIDER = TAB_SPACE + "____________________________________________________________\n";
    public static final String BYE = "bye";
    public static final String BYE_MESSAGE = TAB_SPACE + "Bye. Hope to see you again soon!";
    public static final String HELLO_MESSAGE = TAB_SPACE + "Hello! I'm Duke\n";
    public static final String HELP_MESSAGE = TAB_SPACE + "It seems like you are needing some help.\n";
    public static final String DONE = "done";
    public static final String LIST = "list";
    public static final String TASK_MARKING_MESSAGE = TAB_SPACE + "Nice! I've marked this task as done:";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String TASK_LISTING = TAB_SPACE + "Here are the tasks in your list:";
    public static final String ADDED_TASK_MESSAGE = TAB_SPACE + "Got it. I've added this task:";
    public static final int TODO_WORD_LENGTH = 4;
    public static final int DEADLINE_WORD_LENGTH = 8;
    public static final int EVENT_WORD_LENGTH = 5;
    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final String EMPTY_INPUT_MESSAGE = TAB_SPACE + "☹ OOPS!!! Input cannot be empty";
    public static final String DUMMY_INPUT_MESSAGE = TAB_SPACE + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String LIMIT_REACHED_MESSAGE = TAB_SPACE + "☹ OOPS!!! duke.task.Task limit has reached";
    public static final String WRONG_NUMBER_FORMAT_MESSAGE = TAB_SPACE + "☹ OOPS!!! Wrong number format!";
    public static final String OUT_OF_BOUND_MESSAGE = TAB_SPACE + "☹ OOPS!!! The task's index is out of bound or does not exist";
    public static final String TASK_REMOVED_MESSAGE = TAB_SPACE + "Noted. I've removed this task:";
    public static final String DELETE = "delete";
    public static final String VERTICAL_BAR = "|";
    public static final String TODO_ABBREVIATION = "T";
    public static final String DEADLINE_ABBREVIATION = "D";
    public static final String EVENT_ABBREVIATION = "E";
    public static final String PATH = "data/duke.txt";
    public static final String ONE = "1";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor of the main class Duke
     * @see Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Method to run a Duke program
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // show the divider line ("_______")
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                ui.showNextLine();
            }
        }
    }

    /**
     * Main function of the Duke program
     *
     * @param args main's arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
