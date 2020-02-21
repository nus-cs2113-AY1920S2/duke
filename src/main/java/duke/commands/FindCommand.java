package duke.commands;

import static duke.common.Messages.FILTER_MESSAGE;
import static duke.common.Messages.NO_RESULTS;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    private static String keyword;

    public static String COMMAND_PHRASE = "find (string)";
    public static String COMMAND_USAGE = COMMAND_PHRASE + System.lineSeparator()
            + "-Returns all tasks with the keyword (string) in their description";

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

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
