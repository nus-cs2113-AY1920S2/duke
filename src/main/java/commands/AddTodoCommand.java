package commands;

import data.task.Task;

public class AddTodoCommand extends AddCommand {

    public static final String COMMAND_WORD = "todo";
    public static final char COMMAND_TYPE = 'T';

    private final Task toDo;

    public AddTodoCommand(Task toDo) {
        this.toDo = toDo;
    }

    @Override
    public CommandResult execute() {
        duke.addTask(toDo);


        return new CommandResult(String.format(
                MESSAGE_SUCCESS,
                COMMAND_TYPE,
                toDo.getChar(),
                toDo.getTaskDescription()));
    }

}
