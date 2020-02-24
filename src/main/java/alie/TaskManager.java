package alie;

import alie.task.Task;

import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> taskList;

    public TaskManager () {
        this.taskList = new ArrayList<>();
    }
    public TaskManager (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getNumOfTasks() {
        return taskList.size();
    }

    public ArrayList<Task> getAllTasksAdded() {
        return taskList;
    }

    public Task getTaskFromIndex(int index) {
        return taskList.get(index);
    }

    public void addNewTask(Task newTask) {
        taskList.add(newTask);
    }

    public void markTaskCompleted(int index) {
        Task taskCompleted = taskList.get(index);
        taskList.set(index, taskCompleted.setTaskCompleted(taskCompleted));
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }
}
