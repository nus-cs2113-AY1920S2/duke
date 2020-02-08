import java.util.ArrayList;

public class TaskManager {

    public static final String TASK_ALREADY_SET_ALERT = "Task was already set as done";
    public static final String TASK_MARKED_MESSAGE = "Nice! I've marked this task as done:";
    public static final String LIST_TASKS_MESSAGE = "Here are the tasks in your list:";
    public static final String LIST_EMPTY_MESSAGE = "The list is empty";
    public static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    public static final String DEADLINE_SPECIFIER = "/by ";
    public static final String PERIOD_SPECIFIER = "/at ";

    // Stores all the tasks provided
    ArrayList<Task> tasks = new ArrayList<Task>();

    // Adds a new task with the descriptionWithDetails provided by the user
    public void addTask(TaskType taskType, String descriptionWithDetails){
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
        }
        printLatestTaskAndTotalNumberOfTasks();
    }

    // Prints details about the latest task added along with
    // the total number of tasks present in the list
    private void printLatestTaskAndTotalNumberOfTasks() {
        PrintHelper.printLine();
        PrintHelper.printWithIndentation(TASK_ADDED_MESSAGE);
        PrintHelper.printWithIndentation(tasks.get(tasks.size()-1).getStatusWithDescription(),7);
        PrintHelper.printWithIndentation("Now you have " + tasks.size() + " task" + (tasks.size() != 1?"s ":" ") + "in the list.");
        PrintHelper.printLine();
    }

    // Marks the task denoted by the task as done
    // Also handles exceptions in case the index provided isn't valid
    public void markTask (String[] commandSplit) throws DukeException {
        int taskNumber;
        if (commandSplit.length != 2){
            throw new DukeException(ExceptionType.IndexDoneCommand);
        }
        String taskIndex = commandSplit[1];
        taskNumber = Integer.parseInt(taskIndex);
        // Convert to 0-based index
        taskNumber--;
        if (tasks.get(taskNumber).isDone) {
            printAsAlreadyDone(taskNumber);
        } else {
            markTaskAsDone(taskNumber);
        }
    }

    // Marks the task denoted by a valid task index as done and prints the corresponding message
    public void markTaskAsDone(int taskNumber){
        tasks.get(taskNumber).markAsDone();
        PrintHelper.printLine();
        PrintHelper.printWithIndentation(TASK_MARKED_MESSAGE);
        PrintHelper.printWithIndentation(tasks.get(taskNumber).getStatusWithDescription(),7);
        PrintHelper.printLine();
    }

    // Lists all the tasks provided by user so far
    public void listTasks(){
        boolean isEmpty = (tasks.size() == 0);
        PrintHelper.printLine();
        if (isEmpty) {
            PrintHelper.printWithIndentation(LIST_EMPTY_MESSAGE);
        } else {
            PrintHelper.printWithIndentation(LIST_TASKS_MESSAGE);
            for (int i = 0; i < tasks.size(); i++) {
                PrintHelper.printWithIndentation((i + 1) + ". " + tasks.get(i).getStatusWithDescription());
            }
        }
        PrintHelper.printLine();
    }

    // Prints that the user has already marked the specified task as done previously
    public void printAsAlreadyDone(int index){
        PrintHelper.printLine();
        PrintHelper.printWithIndentation(TASK_ALREADY_SET_ALERT);
        PrintHelper.printWithIndentation(tasks.get(index).getStatusWithDescription(),7);
        PrintHelper.printLine();
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
        if (!isCorrectFormat) {
           throw new DukeException(ExceptionType.InvalidDeadlineDeclaration);
        }
        addTask(TaskType.Deadline, commandSplit[1]);
    }

    // Instructs the task manager to add the ToDo task specified by the user
    //  to the list if the correct format is used
    public void addToDoTask(String[] commandSplit) throws DukeException {
        if (commandSplit.length == 1){
            throw new DukeException(ExceptionType.InvalidToDoDeclaration);
        }
        addTask(TaskType.ToDo, commandSplit[1]);
    }

    // Instructs the task manager to mark the task done if the correct format is used
    public void markTaskAsDone(String[] commandSplit) {
        try{
            markTask(commandSplit);
        } catch (DukeException invalidDoneCommand) {
            invalidDoneCommand.printExceptionMessage();
        } catch (NumberFormatException indexNotInteger) {
            PrintHelper.printIndexNotIntegerAlert();
        } catch (IndexOutOfBoundsException indexOutOfBounds){
            PrintHelper.printInvalidIndexAlert();
        }
    }

    // Instructs the task manager to list the tasks if the correct format is used
    public void listTasks(boolean isCorrectFormat) {
        if (isCorrectFormat) {
            listTasks();
        } else {
            PrintHelper.printInvalidCommand();
        }
    }
}
