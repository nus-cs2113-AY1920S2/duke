package duke.task;

import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Handles the list of tasks by providing methods to interface with the list.
 */
public class TaskList {

    private static final String TASK_ALREADY_SET_ALERT = "Task was already set as done";
    private static final String TASK_MARKED_MESSAGE = "Nice! I've marked this task as done:";
    private static final String LIST_TASKS_MESSAGE = "Here are the tasks in your list:";
    private static final String LIST_EMPTY_MESSAGE = "The list is empty";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String DEADLINE_SPECIFIER = "/by ";
    private static final String PERIOD_SPECIFIER = "/at ";
    public static final String DELETE_COMMAND = "delete";
    public static final String DONE_COMMAND = "done";
    private static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:";
    private static final String SEARCH_EMPTY_MESSAGE = "No tasks match the keyword";
    private static final String FOUND_MATCHED_TASKS_MESSAGE = "Here are the matching tasks in your list:";

    /** Stores all the tasks provided */
    public ArrayList<Task> tasks;

    /**
     * Overloaded Constructor for TaskList Class.
     * It creates a new TaskList with the list of tasks provided by the user.
     *
     * @param tasks The list of tasks provided by the user to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Overloaded Constructor for TaskList Class.
     * It creates a new empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds a new task to the list with the descriptionWithDetails provided by the user.
     *
     * @param taskType Denotes the type of the task to be added.
     * @param descriptionWithDetails Contains the description and other related information of the task to be added.
     */
    public void addTask(TaskType taskType, String descriptionWithDetails) {
        switch (taskType) {
        case ToDo:
            tasks.add(new ToDo(descriptionWithDetails));
            break;
        case Deadline:
            tasks.add(new Deadline(descriptionWithDetails));
            break;
        case Event:
            tasks.add(new Event(descriptionWithDetails));
            break;
        default:
            Ui.printWithIndentation("Error!!!");
            break;
        }
        printLatestTaskAndTotalNumberOfTasks();
    }

    /**
     * Prints details about the latest task added along with the total number of tasks present in the list.
     */
    private void printLatestTaskAndTotalNumberOfTasks() {
        Ui.printLine();
        Ui.printWithIndentation(TASK_ADDED_MESSAGE);
        Ui.printWithIndentation(tasks.get(tasks.size() - 1).getStatusWithDescription(),7);
        Ui.printWithIndentation("Now you have " + tasks.size() + " task"
                + (tasks.size() != 1 ? "s " : " ") + "in the list.");
        Ui.printLine();
    }

    /**
     * Marks the task denoted by the task as done.
     * Also handles exceptions in case the index provided isn't valid.
     *
     * @param commandSplit Contains information related to the index of task to be marked as done.
     * @throws DukeException If wrong format was used for mentioning the index in the done command.
     */
    public void markTask(String[] commandSplit) throws DukeException {
        int taskNumber;
        if (commandSplit.length != 2) {
            throw new DukeException(ExceptionType.InvalidDoneCommandException);
        }
        String taskIndex = commandSplit[1];
        taskNumber = Integer.parseInt(taskIndex);
        // Convert to 0-based index
        taskNumber--;
        if (tasks.get(taskNumber).isDone) {
            printAsAlreadyDone(taskNumber);
        } else {
            markTaskAndPrintMessage(taskNumber);
        }
    }

    /**
     * Deletes task at specified index.
     * Also throws exceptions in case the index provided isn't valid.
     *
     * @param commandSplit Contains information related to the index of task to be deleted.
     * @throws DukeException If wrong format was used for mentioning the index in the delete command.
     */
    public void deleteTaskFromList(String[] commandSplit) throws DukeException {
        int taskNumber;
        if (commandSplit.length != 2) {
            throw new DukeException(ExceptionType.InvalidDeleteCommandException);
        }
        String taskIndex = commandSplit[1];
        taskNumber = Integer.parseInt(taskIndex);
        // Convert to 0-based index
        taskNumber--;
        deleteTaskAtIndex(taskNumber);
    }

    /**
     * Deletes the task at specified index.
     * Also throws exceptions in case the index provided isn't valid.
     *
     * @param taskNumber The index (1-based) of the task to be deleted.
     */
    private void deleteTaskAtIndex(int taskNumber) {
        final String taskStatusWithDescription = tasks.get(taskNumber).getStatusWithDescription();
        Ui.printLine();
        Ui.printWithIndentation(TASK_DELETED_MESSAGE);
        Ui.printWithIndentation(taskStatusWithDescription,7);
        tasks.remove(taskNumber);
        Ui.printWithIndentation("Now you have " + tasks.size() + " task"
                + (tasks.size() != 1 ? "s " : " ") + "in the list.");
        Ui.printLine();
    }


    /**
     * Relays message to {@link #deleteTaskFromList(String[])} to delete the task denoted if the index is valid.
     * Also handles exceptions in case the index provided isn't valid.
     *
     * @param commandSplit Contains information related to the index of task to be deleted.
     * @see #deleteTaskFromList(String[])
     */
    public void deleteTask(String[] commandSplit) {
        try {
            deleteTaskFromList(commandSplit);
        } catch (DukeException invalidDeleteCommand) {
            invalidDeleteCommand.printExceptionMessage();
        } catch (NumberFormatException indexNotInteger) {
            Ui.printIndexNotIntegerAlert(DELETE_COMMAND);
        } catch (IndexOutOfBoundsException indexOutOfBounds) {
            Ui.printInvalidIndexAlert(DELETE_COMMAND);
        }
    }

    /**
     * Marks the task denoted by a valid task index as done and prints the corresponding message.
     *
     * @param taskNumber The index (0-based) of the task to be deleted.
     */
    public void markTaskAndPrintMessage(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        Ui.printLine();
        Ui.printWithIndentation(TASK_MARKED_MESSAGE);
        Ui.printWithIndentation(tasks.get(taskNumber).getStatusWithDescription(),7);
        Ui.printLine();
    }

    /**
     * Prints the current list of tasks along with their related information.
     */
    public void printListOfTasks() {
        boolean isEmpty = (tasks.size() == 0);
        Ui.printLine();
        if (isEmpty) {
            Ui.printWithIndentation(LIST_EMPTY_MESSAGE);
        } else {
            Ui.printWithIndentation(LIST_TASKS_MESSAGE);
            for (int i = 0; i < tasks.size(); i++) {
                Ui.printWithIndentation((i + 1) + ". " + tasks.get(i).getStatusWithDescription());
            }
        }
        Ui.printLine();
    }

    /**
     * Prints that the user has already marked the specified task as done previously.
     *
     * @param taskNumber The index (0-based) of the task to be deleted.
     */
    public void printAsAlreadyDone(int taskNumber) {
        Ui.printLine();
        Ui.printWithIndentation(TASK_ALREADY_SET_ALERT);
        Ui.printWithIndentation(tasks.get(taskNumber).getStatusWithDescription(),7);
        Ui.printLine();
    }

    /**
     * Adds the Event task specified by the user to the list if the correct format is used.
     * Also throws an exception if the wrong Event declaration format is used.
     *
     * @param commandSplit Contains information about the command used by user to create the Event task.
     * @param isOneWordCommand Denotes whether the command used to create a new event task only contains a single word.
     * @throws DukeException If the wrong format is used to create an Event task.
     */
    public void addEventTask(String[] commandSplit, boolean isOneWordCommand) throws DukeException {
        boolean isCorrectFormat = !isOneWordCommand && commandSplit[1].contains(PERIOD_SPECIFIER);
        if (!isCorrectFormat) {
            throw new DukeException(ExceptionType.InvalidEventDeclarationException);
        }
        addTask(TaskType.Event, commandSplit[1]);
    }

    /**
     * Adds the Deadline task specified by the user to the list if the correct format is used.
     * Also throws an exception if the wrong Deadline declaration format is used.
     *
     * @param commandSplit Contains information about the command used by user to create the Deadline task.
     * @param isOneWordCommand Denotes whether the command used to create a new deadline task only contains a single word.
     * @throws DukeException If the wrong format is used to create an Deadline task.
     */
    public void addDeadlineTask(String[] commandSplit, boolean isOneWordCommand) throws DukeException {
        boolean isCorrectFormat = !isOneWordCommand && commandSplit[1].contains(DEADLINE_SPECIFIER);
        if(!isCorrectFormat){
            throw new DukeException(ExceptionType.InvalidDeadlineDeclarationException);
        }
        String[] splitDeadline = commandSplit[1].substring(commandSplit[1].indexOf('/')).split(" ",3);
        boolean hasThreeSegments = (splitDeadline.length == 3);
        boolean hasCorrectDateAndTimeFormat =  hasThreeSegments && (isValidTime(splitDeadline[2]));

        if (!hasCorrectDateAndTimeFormat) {
            throw new DukeException(ExceptionType.InvalidDeadlineDeclarationException);
        }

        try {
            LocalDate date = LocalDate.parse(splitDeadline[1]);
        } catch (DateTimeParseException d) {
            throw new DukeException(ExceptionType.InvalidDeadlineDeclarationException);
        }

        addTask(TaskType.Deadline, commandSplit[1]);
    }


    /**
     * Adds the ToDO task specified by the user to the list if the correct format is used.
     * Also throws an exception if the wrong ToDO declaration format is used.
     *
     * @param commandSplit Contains information about the command used by user to create the ToDo task.
     * @param isOneWordCommand Denotes whether the command used to create a new ToDO task only contains a single word.
     * @throws DukeException If the wrong format is used to create an ToDO task.
     */
    public void addToDoTask(String[] commandSplit, boolean isOneWordCommand) throws DukeException {
        if (isOneWordCommand) {
            throw new DukeException(ExceptionType.InvalidToDoDeclarationException);
        }
        addTask(TaskType.ToDo, commandSplit[1]);
    }

    private boolean isValidTime(String time) {
        boolean isCorrectSize = (time.length() == 4);
        boolean hasCorrectHourFormat = (time.charAt(0) <= '1') || (time.charAt(0) == '2' && time.charAt(1) <= '3');
        boolean hasCorrectMinuteFormat = (time.charAt(2) <= '5');
        return isCorrectSize && hasCorrectHourFormat && hasCorrectMinuteFormat;
    }
    /**
     * Relays message to {@link #markTask(String[])} to mark the task denoted as done if the index is valid.
     * Also handles exceptions in case the index provided isn't valid.
     *
     * @param commandSplit Contains information related to the index of task to be deleted.
     * @see #markTask(String[])
     */
    public void markTaskAsDone(String[] commandSplit) {
        try {
            markTask(commandSplit);
        } catch (DukeException invalidDoneCommand) {
            invalidDoneCommand.printExceptionMessage();
        } catch (NumberFormatException indexNotInteger) {
            Ui.printIndexNotIntegerAlert(DONE_COMMAND);
        } catch (IndexOutOfBoundsException indexOutOfBounds) {
            Ui.printInvalidIndexAlert(DONE_COMMAND);
        }
    }

    /**
     * Instructs {@link #printListOfTasks()} to list the tasks if the correct format is used
     *
     * @param isCorrectFormat Denotes the condition to be satisfied for the list command to be valid.
     * @see #printListOfTasks()
     *
     */
    public void listTasks(boolean isCorrectFormat) {
        if (isCorrectFormat) {
            printListOfTasks();
        } else {
            Ui.printInvalidCommand();
        }
    }

    /**
     * Instructs {@link #printListOfTasksWithKeyword(String)} ()} to list the tasks with the search keyword
     * if the correct format is used
     *
     * @param isCorrectFormat Denotes the condition to be satisfied for the list command to be valid.
     * @param keyword The word used for search.
     * @see #printListOfTasksWithKeyword(String) ().
     */
    public void findTasks(boolean isCorrectFormat, String keyword) {
        if (isCorrectFormat) {
            printListOfTasksWithKeyword(keyword);
        } else {
            Ui.printInvalidCommand();
        }
    }

    /**
     * Performs linear search of the list to find all matched tasks and prints them.
     * Also prints an empty search message if none of the tasks match.
     *
     * @param keyword The word used for search.
     */
    private void printListOfTasksWithKeyword(String keyword) {
        boolean hasNoMatchedTasks = true;
        for (Task task: tasks){
            if(task.hasKeyword(keyword)){
                hasNoMatchedTasks = false;
                break;
            }
        }
        Ui.printLine();
        if (hasNoMatchedTasks) {
            Ui.printWithIndentation(SEARCH_EMPTY_MESSAGE);
        } else {
            Ui.printWithIndentation(FOUND_MATCHED_TASKS_MESSAGE);
            int matchedTaskNumber = 1;
            for (Task task : tasks) {
                if (task.hasKeyword(keyword)) {
                    Ui.printWithIndentation((matchedTaskNumber) + ". " + task.getStatusWithDescription());
                    matchedTaskNumber++;
                }
            }
        }
        Ui.printLine();
    }
}
