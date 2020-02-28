package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadLineFormatException;
import duke.exceptions.BadTaskChoiceFormatException;

/**
 * Class for a delete command that can be executed to delete the task from the <code>TaskList</code>
 */
public class DeleteCommand extends TaskSelectionCommand {
    public static final String EXAMPLE_USAGE = "delete <Task Number>";
    public static final String KEYWORD = "delete";

    public DeleteCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);
    }

    /**
     * Deletes the specified <code>Task</code> from the <Code>TaskList</Code>
     * @throws BadTaskChoiceFormatException if task number does not exist
     */
    public void execute() throws BadTaskChoiceFormatException {
        int taskIndex = getTaskIndex();
        taskList.deleteTask(taskIndex);
    }
}
