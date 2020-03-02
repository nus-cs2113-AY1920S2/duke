package src.main.java;

import src.main.java.duke.task.Task;
import java.util.ArrayList;

/**Contains ArrayList of Task class for program and methods to
 * make tasks within Task list
 *
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addToList(Task task){
        taskList.add(task);
    }

    public Task getTaskFromList(int taskNumber) {
        return taskList.get(taskNumber);
    }

    public void completeTask(int taskNumber) {
        taskList.get(taskNumber).completedTask();
    }

    public void removeTaskFromList(int taskNumber) {
        taskList.remove(taskNumber); }
}
