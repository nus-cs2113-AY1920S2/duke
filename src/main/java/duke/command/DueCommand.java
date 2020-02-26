package duke.command;

import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles the task of searching the list of deadline tasks and printing those which are due on the specified date.
 */
public class DueCommand extends Command {

    /** Denotes whether the command is in the correct format. */
    private boolean isCorrectFormat;
    /** The due date used for search. */
    LocalDate dueDate;

    /**
     * Constructor for DueCommand Class.
     * It creates a new DueCommand Object.
     *
     * @param isCorrectFormat Denotes whether the command is in the correct format.
     * @param commandSplit Contains information about the due date to be searched for later.
     * @throws DukeException If the wrong format is used for the due command.
     */
    public DueCommand(boolean isCorrectFormat, String[] commandSplit) throws DukeException {
        this.commandType = CommandType.DueCommand;
        this.isCorrectFormat = isCorrectFormat;
        if (!isCorrectFormat) {
            throw new DukeException(ExceptionType.InvalidDueCommandException);
        }
        try {
            this.dueDate = LocalDate.parse(commandSplit[1]);
        } catch (DateTimeParseException d) {
            throw new DukeException(ExceptionType.InvalidDueCommandException);
        }
    }

    /**
     * Prints the list of deadline tasks which are due on the specified due date.
     * by calling {@link TaskList#dueTasks(boolean, LocalDate)}
     *
     * @param taskList Contains the list of tasks to be searched on.
     * @throws DukeException Never happens as format check is done when the constructor creates the command.
     * @see TaskList#dueTasks(boolean, LocalDate)
     */
    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        taskList.dueTasks(isCorrectFormat, dueDate);
    }
}
