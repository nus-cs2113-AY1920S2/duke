package duke;

import duke.command.Command;
import duke.task.TaskManager;
import duke.ui.Output;
import duke.utility.InputHandler;


public class Duke {

    /** Printer contains methods to print messages for the user */
    private static Output printer;

    /** duke.command.Command contains the names of each command */
    private static Command command;

    /** Manages the lists of tasks */
    private static TaskManager taskManager;

    /** Handles user input */
    private static InputHandler inputHandler;

    /**
     * Initializes objects to handle the list, display output, and for
     * command information
     */
    public static void init () {

        taskManager = new TaskManager();
        printer = new Output();

        inputHandler = new InputHandler(taskManager, printer);

        taskManager.loadTasks();
    }


    public static void main (String[] args) {

        init();

        printer.greetUser(args);

        String userResponse;

        while (true) {
            userResponse = inputHandler.getUserInput();

            command = inputHandler.processUserInput(userResponse);
            command.execute();
        }

    }

}
