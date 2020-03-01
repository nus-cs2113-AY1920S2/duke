package duke.commands;

import duke.data.TaskList;
import duke.format.DateTime;
import duke.task.Deadline;

import static duke.ui.Messages.addTaskMessage;

/**
 * <h3>Add Deadline Command</h3>
 * A <b>Command</b> to add a <b>Deadline</b> task into the <b>TaskList</b>.
 * @see Command
 * @see Deadline
 * @see TaskList
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String FORMAT = "deadline <task description> /by <date> <time>";

    private final String task;
    private final DateTime deadline;

    public AddDeadlineCommand(String task, DateTime deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    /**
     * Executes the <b>Add Deadline Command</b> to add a <b>Deadline</b> task into the <b>TaskList</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Deadline
     * @see TaskList
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        TaskList.add(new Deadline(task, deadline));
        return new CommandResult(addTaskMessage());
    }
}