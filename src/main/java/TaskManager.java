import java.util.ArrayList;

public class TaskManager {

    public static final String TASK_ALREADY_SET_ALERT = "Task was already set as done";
    public static final String TASK_MARKED_MESSAGE = "Nice! I've marked this task as done:";
    public static final String LIST_TASKS_MESSAGE = "Here are the tasks in your list:";

    // Stores all the tasks provided
    ArrayList<Task> tasks = new ArrayList<Task>();

    // Adds a new task with the descriptionWithDetails provided by the user
    public void addTask(TaskType taskType, String descriptionWithDetails){
        switch (taskType) {
        case ToDo:
            tasks.add(new ToDo(descriptionWithDetails));
            break;
        case Deadline:
            if (descriptionWithDetails.contains("/by ") ) {
                tasks.add(new Deadline(descriptionWithDetails));
            } else {
                PrintHelper.printInvalidDeadlineAlert();
                return;
            }
            break;
        case Event:
            if (descriptionWithDetails.contains("/at ") ) {
                tasks.add(new Event(descriptionWithDetails));
            } else {
                PrintHelper.printInvalidEventAlert();
                return;
            }
            break;
        }

        PrintHelper.printLine();
        PrintHelper.printWithIndentation("Got it. I've added this task:");
        PrintHelper.printWithIndentation(tasks.get(tasks.size()-1).getStatusWithDescription(),7);
        PrintHelper.printWithIndentation("Now you have " + tasks.size() + " task" + (tasks.size() != 1?"s ":" ") + "in the list.");
        PrintHelper.printLine();
    }

    private TaskType findTaskType(String description) {
        String[] split = description.split(" ",2);
        switch (split[0]) {
        case "todo":
            return TaskType.ToDo;
        case "deadline":
            return TaskType.Deadline;
        case "event":
            return TaskType.Event;
        }
        return null;
    }

    // Marks the task denoted by the task as done
    // Also handles exceptions in case the index provided isn't valid
    public void markTask(String taskIndex){
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskIndex);
            // Convert to 0-based index
            taskNumber--;
            if (checkIndexValidity(taskNumber)){
                if (tasks.get(taskNumber).isDone){
                    printAsAlreadyDone(taskNumber);
                } else {
                    markTaskAsDone(taskNumber);
                }
            } else {
                PrintHelper.printInvalidIndexAlert();
            }
        } catch (NumberFormatException e) {
            PrintHelper.printInvalidIntegerAlert();
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
        PrintHelper.printLine();
        PrintHelper.printWithIndentation(LIST_TASKS_MESSAGE);
        for (int i = 0; i < tasks.size(); i++){
            PrintHelper.printWithIndentation( (i+1) + ". " + tasks.get(i).getStatusWithDescription());
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
        return index >= 0 && index < tasks.size();
    }

}
