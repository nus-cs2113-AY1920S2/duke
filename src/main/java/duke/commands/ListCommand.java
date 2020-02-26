package duke.commands;

import duke.tasklist.TaskList;

public class ListCommand extends Command {
    public static final String EXAMPLE_USAGE = "list";
    public static final String KEYWORD = "list";

    public ListCommand(String keyword, String[] tokens, TaskList taskList) {
        super(keyword, tokens, taskList);
        isPersistentCommand = false;
    }

    public void execute() {
        taskList.listTasks();
    }
}
