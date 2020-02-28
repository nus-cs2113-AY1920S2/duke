package duke.commands;

import duke.exceptions.BadLineFormatException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Class for find command
 */
public class FindCommand extends Command {
    public static final String EXAMPLE_USAGE = "find <search term>";
    public static final String KEYWORD = "find";
    private static final String MESSAGE = "Here are your search results:";
    private String targetWord;

    public FindCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);

        if (tokens.length <= 1) {
            throw new BadLineFormatException("Specify a word to search for");
        } else if (tokens.length > 2) {
            throw new BadLineFormatException("Too many tokens. Only one word supported");
        }
        targetWord = tokens[1];
    }

    /**
     * List all tasks that contain target word
     */
    public void execute() {
        String tasks = taskList.getTasksByFilter((Task t) -> t.containsWord(targetWord));
        Ui.printPretty(MESSAGE + tasks);
    }
}
