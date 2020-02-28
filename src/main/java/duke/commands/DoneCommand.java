package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadLineFormatException;
import duke.exceptions.BadTaskChoiceFormatException;

/**
 * Class for a done command that can be executed to mark a task as done
 */
public class DoneCommand extends TaskSelectionCommand {
    public static final String EXAMPLE_USAGE = "done <Task Number>";
    public static final String KEYWORD = "done";

    public DoneCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);
    }

    /**
     * Mark the specified <code>Task</code> as done in the <Code>TaskList</Code>
     * @throws BadTaskChoiceFormatException if task number does not exist
     */
    public void execute() throws BadTaskChoiceFormatException {
        int taskIndex = getTaskIndex();
        taskList.markAsDone(taskIndex);
    }
}
