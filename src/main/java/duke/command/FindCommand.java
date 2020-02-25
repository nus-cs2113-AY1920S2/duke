package duke.command;

import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.task.TaskList;

/**
 * Handles the task of searching the list of tasks and prints those which contain the search keyword.
 */
public class FindCommand extends Command {

    /** Denotes whether the command is in the correct format */
    private boolean isCorrectFormat;
    /** The word used for search */
    private String keyword;

    /**
     * Constructor for FindCommand Class.
     * It creates a new FindCommand Object.
     *
     * @param isCorrectFormat Denotes whether the command is in the correct format.
     * @param commandSplit Contains information about the keyword to be searched for later.
     * @throws DukeException If the wrong format is used for the find command.
     */
    public FindCommand(boolean isCorrectFormat, String[] commandSplit) throws DukeException{
        this.commandType = CommandType.FindCommand;
        this.isCorrectFormat = isCorrectFormat;
        try {
            this.keyword = commandSplit[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ExceptionType.InvalidFindCommandException);
        }

    }

    /**
     * Prints the list of tasks containing the keyword in their description
     * by calling {@link TaskList#findTasks(boolean, String)}
     *
     * @param taskList Contains the list of tasks to be searched on.
     * @throws DukeException Never happens as format check is done when the constructor creates the command.
     * @see TaskList#findTasks(boolean, String)
     */
    @Override
    public void executeCommand(TaskList taskList) throws DukeException {
        taskList.findTasks(isCorrectFormat,keyword);
    }
}
