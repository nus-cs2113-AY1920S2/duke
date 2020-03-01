package duke.command;

import duke.exception.TaskException.TaskListEmptyException;
import duke.task.TaskManager;
import duke.utility.Messages;

/**
 * A class representing a command to list the user tasks.
 */
public class ListCommand extends Command {

    public final static String USAGE = "list";


    /**
     * Initializes the objects to execute the list command
     *
     * @param manager Task manager to list the current tasks.
     */
    public ListCommand (TaskManager manager) {
        super(manager);
    }

    @Override
    public CommandResult execute () {

        String feedback = "";

        try {

            feedback = taskManager.listTasks();

        } catch (TaskListEmptyException e) {
            feedback = Messages.EMPTY_LIST;

        } finally {
            return new CommandResult(feedback);

        }
    }

}
