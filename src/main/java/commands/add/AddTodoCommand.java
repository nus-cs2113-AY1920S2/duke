package commands.add;

import commands.CommandResult;
import data.task.Task;

public class AddTodoCommand extends AddCommand {

    public static final String COMMAND_WORD = "todo";
    public static final char COMMAND_TYPE = 'T';
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Todo task to the DUKE system..\n"
            + "      Example: " + COMMAND_WORD + "read a book";

    private final Task toDoTask;

    public AddTodoCommand(Task toDoTask) {
        this.toDoTask = toDoTask;
    }

    @Override
    public CommandResult execute() {
        duke.addTask(toDoTask);
        return new CommandResult(String.format(
                MESSAGE_TODO_SUCCESS,
                COMMAND_TYPE,
                toDoTask.getChar(),
                toDoTask.getTaskDescription()));
    }
}
