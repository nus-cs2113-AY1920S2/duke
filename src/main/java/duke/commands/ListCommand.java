package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Class for a list command that can be executed to list all tasks
 */
public class ListCommand extends Command {
    public static final String EXAMPLE_USAGE = "list";
    public static final String KEYWORD = "list";
    public static final String MESSAGE = "These are your tasks:";

    public ListCommand(String keyword, String[] tokens, TaskList taskList) {
        super(keyword, tokens, taskList);
        isPersistentCommand = false;
    }

    /**
     * Lists all tasks
     */
    public void execute() {
        String tasks = taskList.getTasksByFilter((Task t) -> true);
        Ui.printPretty(MESSAGE + tasks);
    }
}
