import java.util.ArrayList;

public class TaskManager {

    public static final String TASK_ALREADY_SET_ALERT = "Task was already set as done";
    public static final String TASK_MARKED_MESSAGE = "Nice! I've marked this task as done:";
    public static final String LIST_TASKS_MESSAGE = "Here are the tasks in your list:";
    public static final String LIST_EMPTY_MESSAGE = "The list is empty";
    public static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";

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
    public void markTask(String taskIndex){
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskIndex);
            // Convert to 0-based index
            taskNumber--;
            boolean isValidIndex = checkIndexValidity(taskNumber);
            if (!isValidIndex) {
                PrintHelper.printInvalidIndexAlert();
            } else if (tasks.get(taskNumber).isDone) {
                printAsAlreadyDone(taskNumber);
            } else {
                markTaskAsDone(taskNumber);
            }
        } catch (NumberFormatException e) {
            PrintHelper.printIndexNotIntegerAlert();
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

    // Checks if the provided integer is a valid task index
    public boolean checkIndexValidity(int index){
        boolean isPositive = index >= 0, isLessThanSize = index < tasks.size();
        return isPositive && isLessThanSize;
    }

}
