package commands;

import common.Messages;
import data.task.DeadlineTask;
import data.task.EventTask;
import data.task.Task;
import data.task.TodoTask;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all tasks in the DUKE system as a list with index numbers.\n"
            + "    Example: " + COMMAND_WORD;
    public static final String MESSAGE_EMPTY_LIST = "There is no current task in the list!";

    public static String taskListMessage;

    public ListCommand() {

    }

    @Override
    /**
     * If the list is not empty, return null. Do the display inside printAllTasks() function.
     * If the list is empty, display the empty list message by return a CommandResult object.
     */
    public CommandResult execute() {
        System.out.println(Messages.DIVIDER);
        if (duke.getTaskList().getInternalList().size()>0){
            taskListMessage = Messages.printAllTasks(duke.getTaskList());
            return new CommandResult((taskListMessage));
        } else {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }
    }
}
