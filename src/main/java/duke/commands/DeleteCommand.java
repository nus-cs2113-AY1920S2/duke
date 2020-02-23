package duke.commands;

import duke.data.TaskList;

import static duke.format.Printer.deleteTaskMessage;
import static duke.exception.ExceptionMessage.INVALID_LIST_NUMBER_MESSAGE;


public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            TaskList.delete(index);
            return new CommandResult(deleteTaskMessage(index));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(INVALID_LIST_NUMBER_MESSAGE, true);
        }
    }
}
