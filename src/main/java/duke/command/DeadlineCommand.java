package duke.command;

import duke.exception.TaskException.TaskEmptyDescriptionException;
import duke.exception.TaskException.TaskInvalidDateException;
import duke.task.tasktypes.Deadline;
import duke.task.TaskManager;
import duke.task.tasktypes.Task;
import duke.ui.Ui;
import duke.utility.Messages;

public class DeadlineCommand extends Command {

    private String userInput;
    private final String BULLET = "\t\t\u2023";

    public final static String USAGE = "deadline [description] /by [date/time]";

    public DeadlineCommand (TaskManager manager, Ui printer, String userInput) {
        super(manager, printer);
        this.userInput = userInput;
    }

    /**
     * Executes the add deadline command by checking for the correct
     * format
     */
    @Override
    public CommandResult execute () {

        int indexOfBy = userInput.indexOf("/by");
        String feedback = "";

        if (indexOfBy == -1) {
            feedback = String.format(Messages.INVALID_FORMAT, "deadline");
            feedback += String.format(Messages.PROPER_USAGE, USAGE);

            return new CommandResult(feedback);
        }

        try {

            String taskDescription = getDescription(userInput, indexOfBy);
            String byDate = getDate(userInput, indexOfBy);

            Task toAdd = new Deadline(taskDescription, byDate);
            taskManager.addTask(toAdd);

            feedback = getAddedTaskSuccessfullyMsg(toAdd);

        } catch (TaskEmptyDescriptionException e) {
            feedback = String.format(Messages.EMPTY_FIELD, CMD_ADD_DEADLINE, e.toString());
            feedback += String.format(Messages.PROPER_USAGE, USAGE);

        } catch (TaskInvalidDateException e) {
            feedback = String.format(Messages.EMPTY_FIELD, CMD_ADD_DEADLINE, e.toString());
            feedback += String.format(Messages.PROPER_USAGE, USAGE);

        } finally {
            return new CommandResult(feedback);

        }
    }

    private String getAddedTaskSuccessfullyMsg (Task toPrint) {
        return String.format(Messages.ADDED_TASK, BULLET, toPrint, taskManager.getListSize(),
                taskManager.getTaskListNoun());
    }

    private String getDescription (String userInput, int indexOfBy) throws TaskEmptyDescriptionException {

        String taskDescription = userInput.substring(0, indexOfBy).trim();

        if (taskDescription.isEmpty()) {
            throw new TaskEmptyDescriptionException();
        }

        return taskDescription;
    }

    private String getDate (String userInput, int indexOfBy) throws TaskInvalidDateException {
        String byDate = userInput.substring(indexOfBy + 3).trim();

        if (byDate.isEmpty()) {
            throw new TaskInvalidDateException();
        }

        return byDate;
    }

}
