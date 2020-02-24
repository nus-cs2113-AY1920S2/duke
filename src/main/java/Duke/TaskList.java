package Duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList;
    private ArrayList<Task> lastShownList;
    private int taskListSize;

    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    public ArrayList<Task> getLastShownList() {
        return lastShownList;
    }

    public int getTaskListSize() {
        return taskListSize;
    }

    public TaskList invoke() {
        taskArrayList = new ArrayList<>();
        lastShownList = (ArrayList<Task>) taskArrayList.clone();
        taskListSize = 0;
        return this;
    }
}
