package duke;

import duke.commands.Command;
import duke.exceptions.BadLineFormatException;
import duke.exceptions.BadTaskChoiceFormatException;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The main class. Initialized the system, lets the user enter commands and executes them, then exits.
 */
public class Main {
    private static final String FILE_PATH = "data/tasks.txt";
    private static TaskList taskList;

    /**
     * The main method. Initialize everything, run the main command read/execution loop, exit
     */
    public static void main(String[] args) {
        initialize();
        runLoop();
    }

    /**
     * Initialize the Ui. Greet the user. Initialize the <code>TaskList</code>
     */
    private static void initialize() {
        Ui.initialize();
        Ui.greet();
        taskList = new TaskList(FILE_PATH);
    }

    /**
     * Read a line from the user. Parse the line to a <code>Command</code>. Execute the <code>Command</code>.
     * Save the new list of tasks if necessary. Repeat.
     */
    private static void runLoop() {
        String userInput;
        Command command;

        while (true) {
            userInput = Ui.getNextLine();
            try {
                command = Parser.parseUserInput(userInput, taskList);
                command.execute();
                if (command.getIsPersistentCommand()) {
                    taskList.writeTasksToFile();
                }
            } catch (BadLineFormatException | BadTaskChoiceFormatException e) {
                Ui.printPretty(e.getMessage());
            }
        }
    }
}
