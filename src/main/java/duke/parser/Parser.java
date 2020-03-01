package duke.parser;

import duke.command.*;
import duke.task.TaskManager;
import duke.ui.Ui;

import java.util.Scanner;

import static duke.Duke.inDebugMode;

public class Parser {

    private Scanner input;
    private TaskManager taskManager;
    private Ui printer;

    private final String PROMPT_SYMBOL = "> ";

    public Parser(TaskManager taskManager, Ui printer) {
        input = new Scanner(System.in);
        this.taskManager = taskManager;
        this.printer = printer;
    }

    /**
     * Gets the command from the user and handles it so that it is
     * ready for processing
     *
     * @return input given by the user
     */
    public String getUserInput () {
        if (!inDebugMode) {
            System.out.print(PROMPT_SYMBOL);
        }

        String userInput = input.nextLine();

        return userInput.trim();
    }


    /**
     * Checks the type of command received, if any. Redirects
     * path to specific execute function to process the user's input
     *
     * @param userResponse Raw input as entered by the user
     */
    public Command getCommandFromInput (String userResponse) {

        String cmd = userResponse.split(" ")[0];
        userResponse = userResponse.replace(cmd, "");

        if (cmd.equals(Command.CMD_EXIT)) {
            return new ExitCommand(printer);

        } else if (cmd.equals(Command.CMD_HELP)) {
            return new HelpCommand(printer);

        } else if (cmd.equals(Command.CMD_LIST)) {
            return new ListCommand(taskManager);

        } else if (cmd.equals(Command.CMD_DONE)) {
            return new DoneCommand(taskManager, printer, userResponse);

        } else if (cmd.equals(Command.CMD_ADD_DEADLINE)) {
            return new DeadlineCommand(taskManager, printer, userResponse);

        } else if (cmd.equals(Command.CMD_ADD_EVENT)) {
            return new EventCommand(taskManager, printer, userResponse);

        } else if (cmd.equals(Command.CMD_ADD_TODO)) {
            return new TodoCommand(taskManager, printer, userResponse);

        } else if (cmd.equals(Command.CMD_CLEAR_WINDOW)) {
            return new ClearCommand();

        } else if (cmd.equals(Command.CMD_DELETE)) {
            return new DeleteCommand(taskManager, printer, userResponse);

        } else if (cmd.equals(Command.CMD_FIND)) {
            return new FindCommand(taskManager, printer, userResponse);

        } else {
            return new InvalidCommand(printer);
        }

    }


}
