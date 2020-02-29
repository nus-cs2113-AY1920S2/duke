package duke.commands;

import duke.tasklist.TaskList;
import duke.exceptions.BadTaskChoiceFormatException;

import java.util.regex.Pattern;

/**
 * Class for a done command that can be executed to mark a task as done
 */
public class DoneCommand extends Command {
    public static final Pattern FORMAT = Pattern.compile("^done\\s*\\d+\\s+", Pattern.CASE_INSENSITIVE);
    public static final String EXAMPLE_USAGE = "done 2";
    public static final String ERROR_MESSAGE = "Command needs to be in form: done <Task Number>";
    public static final String KEYWORD = "done";
    private int taskIndex;

    /**
     * @param taskList the <code>TaskList</code>
     * @param taskIndex the index of the task to be marked as done
     */
    public DoneCommand(TaskList taskList, int taskIndex) {
        super(taskList);
        this.taskIndex = taskIndex;
    }

    /**
     * Mark the specified <code>Task</code> as done in the <Code>TaskList</Code>
     * @throws BadTaskChoiceFormatException if task number does not exist
     */
    public void execute() throws BadTaskChoiceFormatException {
        taskList.markAsDone(taskIndex);
    }
}
