package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to terminate the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor to create a new exit command.
     *
     * @param details the parameters of the command
     */
    public ExitCommand (String details) {
        super(details);
    }

    @Override
    public void executeCommand(TaskList tasks) {
        Ui.printByeMessage();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
