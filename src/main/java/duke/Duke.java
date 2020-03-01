package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.task.TaskManager;
import duke.ui.Ui;
import duke.parser.Parser;


public class Duke {

    /** Printer contains methods to print messages for the user */
    private static Ui printer;

    /** duke.command.Command contains the names of each command */
    private static Command command;

    /** Manages the lists of tasks */
    private static TaskManager taskManager;

    /** Handles user input */
    private static Parser parser;

    /** Debug mode for text-ui-test */
    public static boolean inDebugMode = false;

    private static void setDebugMode (String[] args) {
        if (args.length >= 1 && args[0].equals("1")) {
            inDebugMode = true;
            return;
        }
    }

    /**
     * Initializes objects to handle the list, display output, and for
     * command information
     */
    public static void init (String[] args) {

        setDebugMode(args);

        taskManager = new TaskManager();
        printer = new Ui();

        parser = new Parser(taskManager, printer);

        taskManager.loadTasks();
    }


    public static void main (String[] args) {

        init(args);

        printer.greetUser();

        String userResponse;

        while (true) {
            userResponse = parser.getUserInput();

            command = parser.getCommandFromInput(userResponse);
            CommandResult userFeedback = command.execute();

            printer.displayFeedback(userFeedback);
        }

    }

}
