package duke.commands;

import duke.data.TaskList;
import duke.ui.Ui;

import static duke.exception.ExceptionMessages.INVALID_LIST_NUMBER_MESSAGE;
import static duke.ui.Messages.ABORT_DELETE_MESSAGE;
import static duke.ui.Messages.DELETE_TASK_CONFIRMATION_MESSAGE;
import static duke.ui.Messages.PROMPT_VALID_DELETE_CONFIRMATION_MESSAGE;
import static duke.ui.Messages.deleteTaskMessage;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String FORMAT = "delete <list number>";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        try {
            String taskToDelete = TaskList.get(index).getTaskStatus();

            boolean canDelete = new Ui().getConfirmation(DELETE_TASK_CONFIRMATION_MESSAGE(taskToDelete),
                    PROMPT_VALID_DELETE_CONFIRMATION_MESSAGE);

            if (canDelete) {
                TaskList.delete(index);
                return new CommandResult(deleteTaskMessage(taskToDelete));
            } else {
                return new CommandResult(ABORT_DELETE_MESSAGE);
            }
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(INVALID_LIST_NUMBER_MESSAGE, true);
        }
    }
}
