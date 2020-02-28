package duke.commands;

import duke.exceptions.BadLineFormatException;
import duke.tasklist.TaskList;

/**
 * Class for find command
 */
public class FindCommand extends Command {
    public static final String EXAMPLE_USAGE = "find <search term>";
    public static final String KEYWORD = "find";

    public FindCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);

        if (tokens.length <= 1) {
            throw new BadLineFormatException("Specify a word to search for");
        } else if (tokens.length > 2) {
            throw new BadLineFormatException("Too many tokens. Only one word supported");
        }
    }

    /**
     * List all tasks that contain target word
     */
    public void execute() {
        taskList.find(tokens[1]);
    }

}
