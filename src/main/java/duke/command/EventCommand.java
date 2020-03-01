package duke.command;

import duke.exception.TaskException.TaskInvalidDateException;
import duke.exception.TaskException.TaskEmptyDescriptionException;
import duke.task.tasktypes.Event;
import duke.task.TaskManager;
import duke.task.tasktypes.Task;
import duke.utility.Messages;


/**
 * A class representing a command to add an event.
 */
public class EventCommand extends Command {


    private String userInput;
    private final String BULLET = "\t\t\u2023";

    public final static String USAGE = "event [description] /at [date/time]";


    /**
     * Initializes the required objects to execute deadline command
     *
     * @param manager Task manager to add the event to the list
     * @param userInput Raw input containing the event information
     */
    public EventCommand(TaskManager manager, String userInput) {
        super(manager);
        this.userInput = userInput;
    }


    @Override
    public CommandResult execute() {

        int indexOfBy = userInput.indexOf("/at");
        String feedback = "";


        if (indexOfBy == -1) {
            feedback = String.format(Messages.INVALID_FORMAT, "event");
            feedback += String.format(Messages.PROPER_USAGE, USAGE);

            return new CommandResult(feedback);
        }

        try {
            String taskDescription = getDescription(userInput, indexOfBy);
            String eventDate = getDetails(userInput, indexOfBy);

            Task toAdd = new Event(taskDescription, eventDate);

            taskManager.addTask(toAdd);

            feedback = getAddedTaskSuccessfullyMsg(toAdd);

        } catch (TaskEmptyDescriptionException e) {
            feedback = String.format(Messages.EMPTY_FIELD, CMD_ADD_EVENT, e.toString());
            feedback += String.format(Messages.PROPER_USAGE, USAGE);

        } catch (TaskInvalidDateException e) {
            feedback = String.format(Messages.EMPTY_FIELD, CMD_ADD_EVENT, e.toString());
            feedback += String.format(Messages.PROPER_USAGE, USAGE);

        } finally {
            return new CommandResult(feedback);
        }
    }


    /**
     * Formats the message to be displayed in case the event is added
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
     * @param indexOfBy Index of the '/at' separator
     * @return Event description.
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
     * Gets event information from raw user input.
     *
     * @param userInput Raw user input.
     * @param indexOfBy Index of the '/by' separator
     * @return The deadline date.
     * @throws TaskInvalidDateException If byDate is empty
     */
    private String getDetails (String userInput, int indexOfBy) throws TaskInvalidDateException {
        String byDate = userInput.substring(indexOfBy + 3).trim();

        if (byDate.isEmpty()) {
            throw new TaskInvalidDateException();
        }

        return byDate;
    }


}