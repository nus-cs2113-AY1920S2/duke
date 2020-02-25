package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_KEYWORD = "find";
    public static final String FIND_COMMAND_ACK = INDENTATION +
            "Here are the matching tasks in your list: " + System.lineSeparator() + "%1$s";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) throws Exception {
        StringBuilder results = new StringBuilder();
        ArrayList<Task> allTasks = taskLists.getAllTasksAdded();
        int index = 1;
        for (Task task : allTasks) {
            if (search(task).equals(task.getTaskInfo())) {
                results.append(String.format("%s %d.%s", INDENTATION, index,
                        search(task) + System.lineSeparator()));
                index += 1;
            }
        }
        return new CommandResult(String.format(FIND_COMMAND_ACK, results.toString()));
    }

    private String search(Task task) {
        String[] spiltDescription = task.getName().split(" ");
        for (String word : spiltDescription) {
            if (word.equals(keyword)) {
                return task.getTaskInfo();
            }
        }
        return "";
    }


}
