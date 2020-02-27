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
    public static final String END_STRING = "bye";
    private static final String FILE_PATH = "data/tasks.txt";
    private static TaskList taskList;

    /**
     * The main method. Initialize everything, run the main command read/execution loop, exit
     */
    public static void main(String[] args) {
        initialize();
        runLoop();
        Ui.sayGoodbye();
    }

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
        String userInput = Ui.getNextLine();
        Command command;

        while (!userInput.toLowerCase().equals(END_STRING)) {
            try {
                command = Parser.parseLine(userInput, taskList);
                command.execute();
                if (command.getIsPersistentCommand()) {
                    taskList.writeTasksToFile();
                }
            } catch (BadLineFormatException | BadTaskChoiceFormatException e) {
                Ui.printPretty(e.getMessage());
            }

            userInput = Ui.getNextLine();
        }
    }
}
