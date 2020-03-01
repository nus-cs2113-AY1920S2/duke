package duke.commands;

import duke.data.TaskList;

import static duke.ui.Messages.completeTaskMessage;
import static duke.ui.Messages.alreadyCompletedTaskMessage;
import static duke.exception.ExceptionMessages.INVALID_LIST_NUMBER_MESSAGE;

public class DoCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String FORMAT = "done <list number>";

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
