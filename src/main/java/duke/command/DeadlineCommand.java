package duke.command;

import duke.exception.TaskException.TaskEmptyDescriptionException;
import duke.exception.TaskException.TaskInvalidDateException;
import duke.task.tasktypes.Deadline;
import duke.task.TaskManager;
import duke.task.tasktypes.Task;
import duke.utility.Messages;
import java.time.DateTimeException;


/**
 * A class representing a command for adding a deadline.
 */
public class DeadlineCommand extends Command {


    private String userInput;
    private final String BULLET = "\t\t-";

    public final static String USAGE = "deadline [description] /by [dd-mm-yyyy] [hh:mm], 24-hour clock";


    /**
     * Initializes the required objects to execute deadline command
     *
     * @param manager Task manager to add the deadline to the list
     * @param userInput Raw input containing the deadline information
     */
    public DeadlineCommand (TaskManager manager, String userInput) {
        super(manager);
        this.userInput = userInput;
    }


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

        } catch (DateTimeException e) {
            feedback = String.format(Messages.EMPTY_FIELD, CMD_ADD_DEADLINE, e.getLocalizedMessage());
            feedback += String.format(Messages.PROPER_USAGE, USAGE);

        } finally {
            return new CommandResult(feedback);

        }
    }


    /**
     * Formats the message to be displayed in case the deadline is added
     * successfully
     *
     * @param toPrint Task added to the task list.
     * @return Message to display
     */
    private String getAddedTaskSuccessfullyMsg (Task toPrint) {
        return String.format(Messages.ADDED_TASK, BULLET, toPrint, taskManager.getListSize(),
                taskManager.getTaskListNoun());
    }


    /**
     * Gets deadline description from raw user input.
     *
     * @param userInput Raw user input.
     * @param indexOfBy Index of the '/by' separator
     * @return Deadline description.
     * @throws TaskEmptyDescriptionException If taskDescription is empty.
     */
    private String getDescription (String userInput, int indexOfBy) throws TaskEmptyDescriptionException {

        String taskDescription = userInput.substring(0, indexOfBy).trim();

        if (taskDescription.isEmpty()) {
            throw new TaskEmptyDescriptionException();
        }

        return taskDescription;
    }


    /**
     * Gets deadline date from raw user input.
     *
     * @param userInput Raw user input.
     * @param indexOfBy Index of the '/by' separator
     * @return The deadline date.
     * @throws TaskInvalidDateException If byDate is empty
     */
    private String getDate (String userInput, int indexOfBy) throws TaskInvalidDateException {
        String byDate = userInput.substring(indexOfBy + 3).trim();

        if (byDate.isEmpty()) {
            throw new TaskInvalidDateException();
        }

        return byDate;
    }



}
