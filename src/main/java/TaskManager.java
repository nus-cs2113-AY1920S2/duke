import java.util.ArrayList;

public class TaskManager {
    // Stores all the tasks provided
    ArrayList<Task> tasks = new ArrayList<Task>();

    // Adds a new task with the description provided by the user
    public void addTask(String description){
        tasks.add(new Task(description));
        PrintHelper.printLine();
        PrintHelper.printWithIndentation("added: " + description );
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
        PrintHelper.printWithIndentation("Nice! I've marked this task as done: ");
        PrintHelper.printWithIndentation(tasks.get(taskNumber).getStatusWithDescription(),7);
        PrintHelper.printLine();
    }

    // Lists all the tasks provided by user so far
    public void listTasks(){
        PrintHelper.printLine();
        PrintHelper.printWithIndentation("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++){
            PrintHelper.printWithIndentation( (i+1) + ". " + tasks.get(i).getStatusWithDescription());
        }
        PrintHelper.printLine();
    }

    // Prints that the user has already marked the specified task as done previously
    public void printAsAlreadyDone(int index){
        PrintHelper.printLine();
        PrintHelper.printWithIndentation("Task was already set as done");
        PrintHelper.printWithIndentation(tasks.get(index).getStatusWithDescription(),7);
        PrintHelper.printLine();
    }

    // Checks if the provided integer is a valid task index
    public boolean checkIndexValidity(int index){
        return index >= 0 && index < tasks.size();
    }

}
