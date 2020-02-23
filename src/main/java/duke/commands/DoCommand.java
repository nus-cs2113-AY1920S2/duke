package duke.commands;

import duke.data.TaskList;

import static duke.format.Printer.completeTaskMessage;
import static duke.format.Printer.alreadyCompletedTaskMessage;
import static duke.exception.ExceptionMessage.INVALID_LIST_NUMBER_MESSAGE;

public class DoCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private final int index;

    public DoCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            // Checks if task has not been previously done before
            boolean isDoing = TaskList.doTask(index);

            if (isDoing) {
                return new CommandResult(completeTaskMessage(index));
            } else {
                return new CommandResult(alreadyCompletedTaskMessage(index));
            }
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(INVALID_LIST_NUMBER_MESSAGE, true);
        }
    }
}
