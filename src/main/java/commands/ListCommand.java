package commands;

import common.Messages;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = "Here are the tasks in your list:";
    public static final String MESSAGE_EMPTY_LIST = "There is no current task in the list!";
    public static final String MESSAGE_LIST = "  %d. [%c][%c] %s";

    public ListCommand() {

    }

    @Override
    /**
     * If the list is not empty, return null. Do the display inside printAllTasks() function.
     * If the list is empty, display the empty list message by return a CommandResult object.
     */
    public CommandResult execute() {
        System.out.println(Messages.DIVIDER);
        System.out.println(MESSAGE_USAGE);
        if (duke.getTaskList().getInternalList().size()>0){
            printAllTasks();
        } else {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        }
        System.out.println(Messages.DIVIDER);
        return null;
    }

    /**
     * Print all tasks in the task list
     */
    public void printAllTasks(){
        for (int i = 1; i <= duke.getTaskList().getInternalList().size() ; i++) {
            System.out.println(String.format(
                    MESSAGE_LIST,
                    i,
                    duke.getTaskList().getInternalList().get(i-1).getTaskType(),
                    duke.getTaskList().getInternalList().get(i-1).getChar(),
                    duke.getTaskList().getInternalList().get(i-1).getTaskDescription()));
        }
    }

}
