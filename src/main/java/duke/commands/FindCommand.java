package duke.commands;

import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.ui.TextUi;

/**
 * Handles the task of searching the list of tasks and prints those which contain the search keyword.
 */
public class FindCommand extends Command {

    /** The word used for search. */
    String keyword;

    /**
     * Constructor for FindCommand Class.
     * It creates a new FindCommand Object.
     *
     * @param keyword The word used for search.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints the list of tasks containing the keyword in their description.
     * by calling {@link TaskList#findKeyword(String, TaskList)}
     *
     * @param tasklist Contains the list of tasks to be searched on.
     * @throws DukeException Never happens as format check is done when the constructor creates the command.
     */
    @Override
    public void execute(TaskList tasklist) throws DukeException {
        boolean isFound = tasklist.findKeyword(this.keyword, tasklist);
        if (isFound) {
            TextUi.printTaskFound(keyword, tasklist);
        } else {
            TextUi.printKeyNotFound(keyword);
        }
    }
}
