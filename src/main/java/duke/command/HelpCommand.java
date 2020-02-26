package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Displays a list of supported commands along with their formats.
 */
public class HelpCommand extends Command {

    /** The list of commands along with their formats. */
    public static final String listOfCommandsWithFormat = "The list of supported commands along with their formats "
            + "are as follows:\n"
            + "      todo description - Adds a new ToDo task to the list of tasks\n"
            + "      deadline description /by yyyy-mm-dd hhmm - Adds a new Deadline task to the list of tasks\n"
            + "      event description /at location - Adds a new ToDo task to the list of tasks\n"
            + "      delete index - Deletes task at specified index\n"
            + "      done index - Marks the task at specified index as done\n"
            + "      list - Lists all the tasks in the list\n"
            + "      find keyword - Lists all matching tasks in the list containing the keyword\n"
            + "      due yyyy-mm-dd - Lists all deadline tasks which are due on the specified date\n"
            + "      help - Displays a list of supported commands along with their format\n"
            + "      bye - Exits the application";

    /**
     * Constructor for HelpCommand Class.
     * It creates a new HelpCommand Object.
     *
     */
    public HelpCommand() {
        this.commandType = CommandType.HelpCommand;
    }

    /**
     * Displays a list of supported commands along with their formats.
     *
     * @param taskList Contains the list of tasks.
     * @throws DukeException If wrong format is used, But it never happens.
     */
    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        Ui.printLine();
        Ui.printWithIndentation(listOfCommandsWithFormat);
        Ui.printLine();
        System.out.println();
    }

}
