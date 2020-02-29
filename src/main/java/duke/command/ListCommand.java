package duke.command;

import duke.exception.TaskException.TaskListEmptyException;
import duke.task.TaskManager;
import duke.utility.Messages;

public class ListCommand extends Command {

    public final static String USAGE = "list";

    public ListCommand (TaskManager manager) {
        super(manager, null);
    }

    /**
     *  Prints the tasks that are currently in the list
     */
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
