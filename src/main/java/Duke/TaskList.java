package Duke;

import Duke.TaskTypes.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTasks(Task task){
        this.taskList.add(task);
    }

    public int getNumberOfTask() {
        return taskList.size();
    }

    public void markTaskAsDone(int i) {
        taskList.get(i).markAsDone();
    }

    public void removeTask(int i) {
        taskList.remove(i);
    }
}
