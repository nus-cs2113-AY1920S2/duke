package duke.command;

import duke.exception.TaskException.TaskEmptyDescriptionException;
import duke.task.TaskManager;
import duke.task.tasktypes.Task;
import duke.task.tasktypes.Todo;
import duke.ui.Ui;
import duke.utility.Messages;

public class TodoCommand extends Command {

    private String userInput;
    private final String BULLET = "\t\t\u2023";

    public final static String USAGE = "todo [description]";

    public TodoCommand (TaskManager manager, Ui printer, String userInput) {
        super(manager, printer);
        this.userInput = userInput;
    }

    /**
     * Executes the to do command and adds the corresponding task
     * to the list
     */
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

    private String getDescription (String userInput) throws TaskEmptyDescriptionException {

        String taskDescription = userInput.trim();

        if (taskDescription.length() == 0) {
            throw new TaskEmptyDescriptionException();
        }

        return taskDescription;
    }

    private String getAddedTaskSuccessfullyMsg (Task toPrint) {
        return String.format(Messages.ADDED_TASK, BULLET, toPrint, taskManager.getListSize(),
                taskManager.getTaskListNoun());
    }
}
