package duke.parser;

import duke.command.*;
import duke.task.TaskManager;
import duke.ui.Ui;

import java.util.Scanner;

import static duke.Duke.inDebugMode;

/**
 * A class representing a mechanism to get and process user input.
 */
public class Parser {


    private Scanner input;
    private TaskManager taskManager;

    private final String PROMPT_SYMBOL = "> ";


    /**
     * Creates a parser to process user input.
     *
     * @param taskManager Task manager to edit and/or view the task list.
     */
    public Parser(TaskManager taskManager) {
        input = new Scanner(System.in);
        this.taskManager = taskManager;
    }


    /**
     * Gets the command from the user and handles it so that it is
     * ready for processing.
     *
     * @return Input given by the user
     */
    public String getUserInput () {
        if (!inDebugMode) {
            System.out.print(PROMPT_SYMBOL);
        }

        String userInput = input.nextLine();

        return userInput.trim();
    }


    /**
     * Checks the type of command received, if any. Instantiates the
     * corresponding command to be ready to execute.
     *
     * @param userResponse Raw input containing the command.
     */
    public Command getCommandFromInput (String userResponse) {

        String cmd = userResponse.split(" ")[0];
        userResponse = userResponse.replace(cmd, "");

        if (cmd.equals(Command.CMD_EXIT)) {
            return new ExitCommand(new Ui());

        } else if (cmd.equals(Command.CMD_HELP)) {
            return new HelpCommand();

        } else if (cmd.equals(Command.CMD_LIST)) {
            return new ListCommand(taskManager);

        } else if (cmd.equals(Command.CMD_DONE)) {
            return new DoneCommand(taskManager, userResponse);

        } else if (cmd.equals(Command.CMD_ADD_DEADLINE)) {
            return new DeadlineCommand(taskManager, userResponse);

        } else if (cmd.equals(Command.CMD_ADD_EVENT)) {
            return new EventCommand(taskManager, userResponse);

        } else if (cmd.equals(Command.CMD_ADD_TODO)) {
            return new TodoCommand(taskManager, userResponse);

        } else if (cmd.equals(Command.CMD_CLEAR_WINDOW)) {
            return new ClearCommand();

        } else if (cmd.equals(Command.CMD_DELETE)) {
            return new DeleteCommand(taskManager, userResponse);

        } else {
            return new InvalidCommand();
        }

    }


}
