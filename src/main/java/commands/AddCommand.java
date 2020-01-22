package commands;

import data.Task;

public class AddCommand extends Command{

    //public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private final Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute() {
        duke.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getTaskDescription()));
    }
}
