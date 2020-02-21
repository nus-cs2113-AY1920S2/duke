package duke.commands;

import static duke.common.Messages.FILTER_MESSAGE;
import static duke.common.Messages.NO_RESULTS;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;

/**
 * Filters the list of tasks based on a keyword provided by the user.
 */
public class FindCommand extends Command {

    /** Keyword to use in the filter */
    private static String keyword;

    /** Format of the command */
    public static String COMMAND_PHRASE = "find (string)";

    /** Usage of the command */
    public static String COMMAND_USAGE = COMMAND_PHRASE + System.lineSeparator()
            + "-Returns all tasks with the keyword (string) in their description";

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Returns a <code>CommandResult</code> with feedback to the user and the filtered list of tasks if
     * matching tasks are found.
     *
     * @param tasks <code>TaskList</code> object that the <code>Command</code> will execute on.
     * @param textUi <code>Ui</code> object that is being used to display output to the user.
     * @param storage <code>Storage</code> object that is able to access tasks saved in memory.
     * @return <code>CommandResult</code>.
     */
    public CommandResult execute(TaskList tasks, Ui textUi, Storage storage) {
        ArrayList<Task> filteredList = new ArrayList<>();
        int size = tasks.getSize();
        for (int i = 0; i < size; i++) {
            Task unfilteredTask = tasks.getIndex(i);
            if (unfilteredTask.getDescription().contains(keyword)) {
                filteredList.add(unfilteredTask);
            }
        }
        if (filteredList.size() == 0) {
            String feedback = NO_RESULTS;
            return new CommandResult(feedback);
        } else {
            String feedback = FILTER_MESSAGE;
            return new CommandResult(feedback,filteredList);
        }
    }
}
