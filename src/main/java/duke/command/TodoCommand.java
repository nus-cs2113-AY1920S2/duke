package duke.command;

import duke.exception.TaskException.TaskEmptyDescriptionException;
import duke.task.TaskManager;
import duke.task.tasktypes.Task;
import duke.task.tasktypes.Todo;
import duke.utility.Messages;


/**
 * A class representing a command to add a todo.
 */
public class TodoCommand extends Command {


    private String userInput;
    private final String BULLET = "\t\t-";

    public final static String USAGE = "todo [description]";


    /**
     * Creates a Todo command.
     *
     * @param manager Task manager to add todo to the list.
     * @param userInput Raw user input containing the todo description.
     */
    public TodoCommand (TaskManager manager, String userInput) {
        super(manager);
        this.userInput = userInput;
    }


    @Override
    public CommandResult execute() {

        String feedback = "";

        try {
            String taskDescription = getDescription(userInput);

            Task toAdd = new Todo(taskDescription);
            taskManager.addTask(toAdd);

            feedback = getAddedTaskSuccessfullyMsg(toAdd);

        } catch (TaskEmptyDescriptionException e) {

            feedback = String.format(Messages.EMPTY_FIELD, CMD_ADD_TODO, e.toString());
            feedback += String.format(Messages.PROPER_USAGE, USAGE);

        } finally {
            return new CommandResult(feedback);
        }

    }


    /**
     * Gets todo description from raw user input.
     *
     * @param userInput Raw user input.
     * @return Todo description.
     * @throws TaskEmptyDescriptionException If taskDescription is empty.
     */
    private String getDescription (String userInput) throws TaskEmptyDescriptionException {

        String taskDescription = userInput.trim();

        if (taskDescription.length() == 0) {
            throw new TaskEmptyDescriptionException();
        }

        return taskDescription;
    }


    /**
     * Formats the message to be displayed in case the todo is added
     * successfully
     *
     * @param toPrint Task added to the task list.
     * @return Message to display
     */
    private String getAddedTaskSuccessfullyMsg (Task toPrint) {
        return String.format(Messages.ADDED_TASK, BULLET, toPrint, taskManager.getListSize(),
                taskManager.getTaskListNoun());
    }
}
