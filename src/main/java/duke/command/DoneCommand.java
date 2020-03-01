package duke.command;

import duke.exception.TaskException.TaskOutOfBoundsException;
import duke.exception.TaskException.TaskListEmptyException;
import duke.exception.TaskException.TaskAlreadyMarkedException;
import duke.task.TaskManager;
import duke.task.tasktypes.Task;
import duke.utility.Messages;

/**
 * A class representing a command to mark a task as done.
 */
public class DoneCommand extends Command {


    private String userInput;
    public final static String USAGE = "done [task number]";


    /**
     * Creates a done command.
     *
     * @param manager Task manager to mark task as done.
     * @param userInput Raw input containing the index of the task.
     */
    public DoneCommand (TaskManager manager, String userInput) {
        super(manager);
        this.userInput = userInput;
    }


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


    /**
     * Formats the user feedback to be displayed when the given index is
     * out of bounds.
     *
     * @param targetIndex Index of the task to mark as done
     * @return Feedback to be displayed
     */
    private String getTaskOutOfBoundsMsg (int targetIndex) {
        return String.format(Messages.TASK_OUT_OF_BOUNDS, targetIndex + 1, taskManager.getTaskListNounDescriptor(),
                taskManager.getListSize(), taskManager.getTaskListNoun());
    }


    /**
     * Formats feedback in case the task is already marked as done.
     *
     * @param task Task specified by the user.
     * @return Feedback to be displayed.
     */
    private String getTaskAlreadyDone (Task task) {
        return String.format(Messages.DONE_MARKED_BEFORE, task);
    }


    /**
     * Formats feedback in case the task is marked successfully.
     *
     * @param toPrint Task that is marked as done.
     * @return Feedback to be displayed.
     */
    private String getMarkedSuccessfullyMsg (Task toPrint) {
        String msg = String.format(Messages.DONE_MARKED_SUCCESSFUL, toPrint);

        return msg;
    }
}
