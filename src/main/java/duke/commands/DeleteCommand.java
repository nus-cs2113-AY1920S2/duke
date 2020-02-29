package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadTaskChoiceFormatException;

import java.util.regex.Pattern;

/**
 * Class for a delete command that can be executed to delete the task from the <code>TaskList</code>
 */
public class DeleteCommand extends Command {
    public static final Pattern FORMAT = Pattern.compile("^delete\\s+\\d+\\s*", Pattern.CASE_INSENSITIVE);
    public static final String EXAMPLE_USAGE = "delete 3";
    public static final String ERROR_MESSAGE = "Command needs to be in form: delete <Task Number>";
    public static final String KEYWORD = "delete";
    private int taskIndex;

    /**
     * @param taskList the <code>TaskList</code>
     * @param taskIndex the index of the task to be deleted
     */
    public DeleteCommand(TaskList taskList, int taskIndex) {
        super(taskList);
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the specified <code>Task</code> from the <Code>TaskList</Code>
     * @throws BadTaskChoiceFormatException if task number does not exist
     */
    public void execute() throws BadTaskChoiceFormatException {
        taskList.deleteTask(taskIndex);
    }
}
