package duke.commands;

import duke.data.TaskList;
import duke.task.ToDo;

import static duke.ui.Messages.addTaskMessage;

/**
 * <h3>Add To Do Command</h3>
 * A <b>Command</b> to add a <b>To Do</b> task into the <b>TaskList</b>.
 * @see Command
 * @see ToDo
 * @see TaskList
 */
public class AddToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String FORMAT = "todo <task description>";

    private final String task;

    public AddToDoCommand(String task) {
        this.task = task;
    }

    /**
     * Executes the <b>Add To Do Command</b> to add a <b>To Do</b> task into the <b>TaskList</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see ToDo
     * @see TaskList
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        TaskList.add(new ToDo(task));
        return new CommandResult(addTaskMessage());
    }
}
