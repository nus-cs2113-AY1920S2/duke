package duke.commands;

import duke.data.TaskList;

import static duke.format.Printer.addTaskMessage;

public class AddToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private final String task;

    public AddToDoCommand(String task) {
        this.task = task;
    }

    @Override
    public CommandResult execute() {
        TaskList.addToDo(task);
        return new CommandResult(addTaskMessage());
    }
}
