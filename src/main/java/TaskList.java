import data.*;

import java.util.ArrayList;

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

    public boolean getTaskDoneStatus(int i) {
        return this.tasks.get(i).getIsDone();
    }

    public void markTaskAsDone(int i) {
        this.tasks.get(i).markAsDone();
    }

    public String getTaskDescription(int i) {
        return this.tasks.get(i).getDescription();
    }

    public String getTaskStatusIcon(int i) {
        return this.tasks.get(i).getStatusIcon();
    }

    public void addTask(Task taskToAdd) {
        this.tasks.add(taskToAdd);
    }

    public Task removeTask(int i) {
        return this.tasks.remove(i);
    }
}
