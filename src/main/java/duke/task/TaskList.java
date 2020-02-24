package duke.task;

import duke.exception.DukeException;
import duke.exception.ExceptionType;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    public static final String TASK_ALREADY_SET_ALERT = "Task was already set as done";
    public static final String TASK_MARKED_MESSAGE = "Nice! I've marked this task as done:";
    public static final String LIST_TASKS_MESSAGE = "Here are the tasks in your list:";
    public static final String LIST_EMPTY_MESSAGE = "The list is empty";
    public static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    public static final String DEADLINE_SPECIFIER = "/by ";
    public static final String PERIOD_SPECIFIER = "/at ";
    public static final String DELETE_COMMAND = "delete";
    public static final String DONE_COMMAND = "done";
    public static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:";

    // Stores all the tasks provided
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<Task>();
    }


    // Adds a new task with the descriptionWithDetails provided by the user
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

    // Prints details about the latest task added along with
    // the total number of tasks present in the list
    private void printLatestTaskAndTotalNumberOfTasks() {
        Ui.printLine();
        Ui.printWithIndentation(TASK_ADDED_MESSAGE);
        Ui.printWithIndentation(tasks.get(tasks.size() - 1).getStatusWithDescription(),7);
        Ui.printWithIndentation("Now you have " + tasks.size() + " task"
                + (tasks.size() != 1 ? "s " : " ") + "in the list.");
        Ui.printLine();
    }

    // Marks the task denoted by the task as done
    // Also handles exceptions in case the index provided isn't valid
    public void markTask(String[] commandSplit) throws DukeException {
        int taskNumber;
        if (commandSplit.length != 2) {
            throw new DukeException(ExceptionType.InvalidDoneCommand);
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

    // Relays message to another method to delete  the task denoted
    // Also throws exceptions in case the index provided isn't valid
    public void deleteTaskFromList(String[] commandSplit) throws DukeException {
        int taskNumber;
        if (commandSplit.length != 2) {
            throw new DukeException(ExceptionType.InvalidDeleteCommand);
        }
        String taskIndex = commandSplit[1];
        taskNumber = Integer.parseInt(taskIndex);
        // Convert to 0-based index
        taskNumber--;
        deleteTaskAtIndex(taskNumber);
    }

    // Deletes the task at specified index
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


    // Instructs the task manager to delete the task if the correct format is used
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

    // Marks the task denoted by a valid task index as done and prints the corresponding message
    public void markTaskAndPrintMessage(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        Ui.printLine();
        Ui.printWithIndentation(TASK_MARKED_MESSAGE);
        Ui.printWithIndentation(tasks.get(taskNumber).getStatusWithDescription(),7);
        Ui.printLine();
    }

    // Lists all the tasks provided by user so far
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

    // Prints that the user has already marked the specified task as done previously
    public void printAsAlreadyDone(int index) {
        Ui.printLine();
        Ui.printWithIndentation(TASK_ALREADY_SET_ALERT);
        Ui.printWithIndentation(tasks.get(index).getStatusWithDescription(),7);
        Ui.printLine();
    }

    // Instructs the task manager to add the Event task specified by the user
    // to the list if the correct format is used
    public void addEventTask(String[] commandSplit, boolean isOneWordCommand) throws DukeException {
        boolean isCorrectFormat = !isOneWordCommand && commandSplit[1].contains(PERIOD_SPECIFIER);
        if (!isCorrectFormat) {
            throw new DukeException(ExceptionType.InvalidEventDeclaration);
        }
        addTask(TaskType.Event, commandSplit[1]);
    }

    // Instructs the task manager to add the Deadline task specified by the user
    // to the list if the correct format is used
    public void addDeadlineTask(String[] commandSplit, boolean isOneWordCommand) throws DukeException {
        boolean isCorrectFormat = !isOneWordCommand && commandSplit[1].contains(DEADLINE_SPECIFIER);
        if(!isCorrectFormat){
            throw new DukeException(ExceptionType.InvalidDeadlineDeclaration);
        }
        String[] splitDeadline = commandSplit[1].substring(commandSplit[1].indexOf('/')).split(" ",3);
        boolean hasThreeSegments = (splitDeadline.length == 3);
        boolean hasCorrectDateAndTimeFormat =  hasThreeSegments && (isValidTime(splitDeadline[2]));

        if (!hasCorrectDateAndTimeFormat) {
            throw new DukeException(ExceptionType.InvalidDeadlineDeclaration);
        }

        try {
            LocalDate date = LocalDate.parse(splitDeadline[1]);
        } catch (DateTimeParseException d) {
            throw new DukeException(ExceptionType.InvalidDeadlineDeclaration);
        }

        addTask(TaskType.Deadline, commandSplit[1]);
    }

    private boolean isValidTime(String time) {
        boolean isCorrectSize = (time.length() == 4);
        boolean hasCorrectHourFormat = (time.charAt(0) <= '1') || (time.charAt(0) == '2' && time.charAt(1) <= '3');
        boolean hasCorrectMinuteFormat = (time.charAt(2) <= '5');
        return isCorrectSize && hasCorrectHourFormat && hasCorrectMinuteFormat;
    }

    // Instructs the task manager to add the ToDo task specified by the user
    //  to the list if the correct format is used
    public void addToDoTask(String[] commandSplit, boolean isOneWordCommand) throws DukeException {
        if (isOneWordCommand) {
            throw new DukeException(ExceptionType.InvalidToDoDeclaration);
        }
        addTask(TaskType.ToDo, commandSplit[1]);
    }

    // Instructs the task manager to mark the task done if the correct format is used
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

    // Instructs the task manager to list the tasks if the correct format is used
    public void listTasks(boolean isCorrectFormat) {
        if (isCorrectFormat) {
            printListOfTasks();
        } else {
            Ui.printInvalidCommand();
        }
    }
}
