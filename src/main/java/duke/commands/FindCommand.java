package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.regex.Pattern;

/**
 * Class for find command
 */
public class FindCommand extends Command {
    public static final Pattern FORMAT = Pattern.compile("^find\\s+\\w\\s*", Pattern.CASE_INSENSITIVE);
    public static final String EXAMPLE_USAGE = "find homework>";
    public static final String ERROR_MESSAGE = "Command needs to be in form: find <search term>";
    public static final String KEYWORD = "find";
    private static final String MESSAGE = "Here are your search results:";
    private String targetWord;

    public FindCommand(TaskList taskList, String targetWord) {
        super(taskList);
        this.targetWord = targetWord;
    }

    /**
     * List all tasks that contain target word
     */
    public void execute() {
        String tasks = taskList.getTasksByFilter((Task t) -> t.containsWord(targetWord));
        Ui.printPretty(MESSAGE + tasks);
    }
}
