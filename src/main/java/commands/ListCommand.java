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
    public static final String MESSAGE_TODO_LIST = "  %d. [%c][%c] %s";
    public static final String MESSAGE_DEADLINE_LIST = "  %d. [%c][%c] %s (%s)";
    public static final String MESSAGE_EVENT_LIST = "  %d. [%c][%c] %s (%s)";

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
            Task task = duke.getTaskList().getInternalList().get(i-1);
            if (task instanceof TodoTask) {
                printTodoTask((TodoTask) task, i);
            } else if (task instanceof DeadlineTask) {
                printDeadlineTask((DeadlineTask) task, i);
            } else if( task instanceof EventTask) {
                printEventTask((EventTask) task, i);
            }
        }
    }

    public void printTodoTask(TodoTask todoTask, int index){
        System.out.println(String.format(
                MESSAGE_TODO_LIST,
                index,
                todoTask.getTaskType(),
                todoTask.getChar(),
                todoTask.getTaskDescription()));
    }

    public void printDeadlineTask(DeadlineTask deadlineTask, int index){
        System.out.println(String.format(
                MESSAGE_DEADLINE_LIST,
                index,
                deadlineTask.getTaskType(),
                deadlineTask.getChar(),
                deadlineTask.getTaskDescription(),
                deadlineTask.getTaskDeadline()));
    }

    public void printEventTask(EventTask eventTask, int index){
        System.out.println(String.format(
                MESSAGE_EVENT_LIST,
                index,
                eventTask.getTaskType(),
                eventTask.getChar(),
                eventTask.getTaskDescription(),
                eventTask.getTaskStartTime()));
    }

}
