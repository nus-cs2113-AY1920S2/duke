package alie.commands;

import alie.Storage;
import alie.TaskManager;
import alie.Ui;
import alie.task.Task;

import java.util.ArrayList;

/**
 * Commands given by user to be executed
 */
public abstract class Command {
    protected static final String RANGE_OF_VALID_TASKS_INDEX_MSG = "Any number from 1 --- %1$s";
    protected static final String NO_TASK_MSG = "Please add tasks first.";

    public String ADD_TASK_ACK =
            "Got it. I've added this task:" + System.lineSeparator() + "  %1$s" +
                    System.lineSeparator() + "Now you have %2$s tasks in the list.";
    public static final String DELETE_TASK_ACK =
            "Noted. I've removed this task:" + System.lineSeparator() + "  %1$s" +
                    System.lineSeparator() + "Now you have %2$s tasks in the list.";
    public static final String DONE_ACK =
            "Nice! I've marked this task as done:" + System.lineSeparator() + "  %1$s. %2$s";
    public static final String FIND_COMMAND_ACK =
            "Here are the matching tasks in your list: " + System.lineSeparator() + "%1$s";

    /**
     * Perform the desired actions of the specific Command.
     * @param taskLists TaskManager containing the user's tasks.
     * @param ui Ui to deal with any input and output when required.
     * @param storage Storage to save data when required.
     * @return Result of executing the command.
     * @throws Exception Any exception that could be encountered while executing command.
     */
    public abstract CommandResult execute(TaskManager taskLists, Ui ui, Storage storage) throws
            Exception;

    /**
     * To convert a integer from one-based numbering to zero-based numbering.
     * @param index One-based integer to be converted.
     * @return Integer with zero-based numbering
     */
    protected int convertToZeroBased (int index) {
        return index - 1;
    }

    /**
     * To convert a integer from zero-based numbering to one-based numbering.
     * @param index Zero-based integer to be converted.
     * @return Integer with one-based numbering
     */
    protected int convertToOneBased (int index) {
        return index + 1;
    }

    /**
     * Obtain a range of numbers that is valid for usage on taskList
     * @param taskList The interested list in TaskManager to find the range of values
     * @return A string with the range of valid numbers.
     */
    protected String getRangeOfValidIndex(TaskManager taskList) {
        int maxTasks = taskList.getNumOfTasks();
        if (maxTasks > 0) {
            return String.format(RANGE_OF_VALID_TASKS_INDEX_MSG, maxTasks);
        } else {
            return NO_TASK_MSG;
        }
    }

    /**
     * Converting taskList into a string for printing.
     * @param targetTaskList TaskList containing the tasks that needs to be converted into a String.
     * @param originalTaskList TaskList that contains all the tasks added. Used as reference for
     *                         finding the correct index of the task to be listed in targetTaskList.
     * @return String containing all the tasks in taskList with proper spacing and line separator.
     */
    public String stringAllTasksInList(ArrayList<Task> targetTaskList,
                                       ArrayList<Task> originalTaskList) {
        StringBuilder allTasks = new StringBuilder();
        if (targetTaskList.size() == 0) {
            allTasks.append(Ui.NO_TASK_FOUND_MSG);
        } else {
            allTasks.append(Ui.TASK_FOUND_MSG);
            for (Task task : targetTaskList) {
                allTasks.append(String.format("%d.%s",
                        convertToOneBased(originalTaskList.indexOf(task)),
                        task.getTaskInfo() + System.lineSeparator()));
            }
        }
        return allTasks.toString();
    }
}
