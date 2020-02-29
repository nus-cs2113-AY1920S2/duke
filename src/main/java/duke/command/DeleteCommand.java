package duke.command;

import duke.exception.TaskException.TaskOutOfBoundsException;
import duke.exception.TaskException.TaskListEmptyException;

import duke.task.TaskManager;
import duke.task.tasktypes.Task;
import duke.ui.Ui;
import duke.utility.Messages;

public class DeleteCommand extends Command {

    private String userInput;
    public final static String USAGE = "delete [task number]";

    public DeleteCommand (TaskManager manager, Ui printer, String userInput) {
        super(manager, printer);
        this.userInput = userInput;
    }


    @Override
    public CommandResult execute () {

        String numberComponent = userInput.substring(userInput.indexOf(" ") + 1).trim();
        String feedback = "";

        if (numberComponent.isEmpty()) {
            return new CommandResult(Messages.EMPTY_DELETE);
        }

        try {

            int taskIndex = Integer.parseInt(numberComponent) - 1;
            Task toPrint = taskManager.removeTask(taskIndex);

            feedback = getTaskRemovedMsg(toPrint);

        } catch (TaskOutOfBoundsException e) {
            String userFeedback = getTaskOutOfBoundsMsg(e.getTargetIndex());
            feedback = userFeedback;

        } catch (TaskListEmptyException e) {
            feedback = Messages.DELETE_EMPTY_LIST;

        } catch (NumberFormatException e) {
            feedback = String.format(Messages.NOT_A_NUMBER_FOR_TASK, numberComponent);
            feedback += String.format(Messages.PROPER_USAGE, USAGE);

        } finally {
            return new CommandResult(feedback);
        }
    }

    private String getTaskRemovedMsg (Task toPrint) {

        int size = taskManager.getListSize();
        String msg = String.format(Messages.DELETE_TASK, toPrint);

        if (size > 0) {
            msg += String.format(Messages.CURRENT_TASK_NUMBER, size, taskManager.getTaskListNoun());
        } else {
            msg += String.format(Messages.LIST_IS_EMPTY_NOW);
        }

        return msg;
    }

    private String getTaskOutOfBoundsMsg (int targetIndex) {
        return String.format(Messages.TASK_OUT_OF_BOUNDS, targetIndex + 1, taskManager.getTaskListNounDescriptor(),
                taskManager.getListSize(), taskManager.getTaskListNoun());
    }

}
