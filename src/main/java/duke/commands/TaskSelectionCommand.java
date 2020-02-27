package duke.commands;

import duke.exceptions.BadLineFormatException;
import duke.exceptions.BadTaskChoiceFormatException;
import duke.tasklist.TaskList;

/**
 * Abstract class that extends regular Command class. Used for commands that need to specify a task number
 * e.g. delete, done
 */
public abstract class TaskSelectionCommand extends Command {
    public TaskSelectionCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);
        if (tokens.length <= 1) {
            throw new BadLineFormatException("Specify a task by entering a task number");
        } else if (tokens.length > 2) {
            throw new BadLineFormatException("Too many tokens");
        }
    }

    /**
     * Get the index in taskList based on user input
     * @return index into taskList parsed from user input
     * @throws BadTaskChoiceFormatException if the user inputs something NaN as a task number
     */
    protected int getTaskIndex() throws BadTaskChoiceFormatException {
        try {
            return Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BadTaskChoiceFormatException("Task number is not parsable");
        }
    }
}
