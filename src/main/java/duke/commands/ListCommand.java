package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.regex.Pattern;

/**
 * Class for a list command that can be executed to list all tasks
 */
public class ListCommand extends Command {
    public static final Pattern FORMAT = Pattern.compile("^list\\s*", Pattern.CASE_INSENSITIVE);
    public static final String EXAMPLE_USAGE = "list";
    public static final String ERROR_MESSAGE = "Command needs to be in form: list";
    public static final String KEYWORD = "list";
    public static final String MESSAGE = "These are your tasks:";

    public ListCommand(TaskList taskList) {
        super(taskList);
        this.isPersistentCommand = false;
    }

    /**
     * Lists all tasks
     */
    public void execute() {
        String tasks = taskList.getTasksByFilter((Task t) -> true);
        Ui.printPretty(MESSAGE + tasks);
    }
}
