package duke.commands;

import duke.data.TaskList;
import duke.task.ToDo;

import static duke.ui.Messages.addTaskMessage;

public class AddToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String FORMAT = "todo <task description>";

    private final String task;

    public AddToDoCommand(String task) {
        this.task = task;
    }

    @Override
    public CommandResult execute() {
        TaskList.add(new ToDo(task));
        return new CommandResult(addTaskMessage());
    }
}
