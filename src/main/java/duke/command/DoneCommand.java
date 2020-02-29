package duke.command;

import duke.exception.TaskException.TaskOutOfBoundsException;
import duke.exception.TaskException.TaskListEmptyException;
import duke.exception.TaskException.TaskAlreadyMarkedException;
import duke.task.TaskManager;
import duke.task.tasktypes.Task;
import duke.ui.Ui;
import duke.utility.Messages;

public class DoneCommand extends Command {

    private String userInput;
    public final static String USAGE = "done [task number]";

    public DoneCommand (TaskManager manager, Ui printer, String userInput) {
        super(manager, printer);
        this.userInput = userInput;
    }

    /**
     * Processes user input and marks the task as done
     */
    @Override
    public CommandResult execute () {

        String numberComponent = userInput.substring(userInput.indexOf(" ") + 1).trim();
        String feedback = "";

        if (numberComponent.isEmpty()) {
            return new CommandResult(Messages.EMPTY_DONE);
        }

        try {

            int taskIndex = Integer.parseInt(numberComponent) - 1;

            Task toPrint = taskManager.markTaskAsDone(taskIndex);

            feedback = getMarkedSuccessfullyMsg(toPrint);

        } catch (TaskListEmptyException e) {
            feedback = Messages.DONE_EMPTY_LIST;

        } catch (TaskOutOfBoundsException e) {
            feedback = getTaskOutOfBoundsMsg(e.getTargetIndex());

        } catch (TaskAlreadyMarkedException e) {
            feedback = getTaskAlreadyDone(e.getTask());

        } catch (NumberFormatException e) {
            feedback = String.format(Messages.NOT_A_NUMBER_FOR_TASK, numberComponent);
            feedback += String.format(Messages.PROPER_USAGE, USAGE);

        } finally {
            return new CommandResult(feedback);

        }
    }

    private String getTaskOutOfBoundsMsg (int targetIndex) {
        return String.format(Messages.TASK_OUT_OF_BOUNDS, targetIndex + 1, taskManager.getTaskListNounDescriptor(),
                taskManager.getListSize(), taskManager.getTaskListNoun());
    }

    private String getTaskAlreadyDone (Task task) {
        return String.format(Messages.DONE_MARKED_BEFORE, task);
    }

    private String getMarkedSuccessfullyMsg (Task toPrint) {
        String msg = String.format(Messages.DONE_MARKED_SUCCESSFUL, toPrint);

        return msg;
    }
}
