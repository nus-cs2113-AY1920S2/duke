package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_KEYWORD = "find";


    private String keyword;

    /**
     * Default constructor
     * @param keyword The String that user wants to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) {
        ArrayList<Task> matchKeywordTasks = new ArrayList<>();
        ArrayList<Task> allTasks = taskLists.getAllTasksAdded();
        for (Task task : allTasks) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                matchKeywordTasks.add(task);
            }
        }
        return new CommandResult(String.format(stringAllTasksInList(matchKeywordTasks, allTasks),
                FIND_COMMAND_ACK));
    }
}
