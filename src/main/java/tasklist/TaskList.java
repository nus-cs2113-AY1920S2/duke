package tasklist;

import data.Task;

import java.util.ArrayList;

/**
 * This class is a list of {@link Task} objects. It acts as an abstract data type, various operations can be
 * conducted on the TaskList object like adding/removing Tasks or getting the total number of Tasks.
 * <p></p>
 * <p>
 * It can also execute operations on individual Tasks; you can get or change the completion status of a Task.
 * </p>
 * @see ArrayList
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> taskListFromSave) {
        this.tasks = taskListFromSave;
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public boolean getTaskDoneStatus(int taskNumber) {
        return this.tasks.get(taskNumber).getIsDone();
    }

    public void markTaskAsDone(int taskNumber) {
        this.tasks.get(taskNumber).markAsDone();
    }

    public String getTaskDescription(int taskNumber) {
        return this.tasks.get(taskNumber).getDescription();
    }

    public String getTaskStatusIcon(int taskNumber) {
        return this.tasks.get(taskNumber).getStatusIcon();
    }

    public void addTask(Task taskToAdd) {
        this.tasks.add(taskToAdd);
    }

    public Task deleteTask(int taskNumber) {
        return this.tasks.remove(taskNumber);
    }
}
