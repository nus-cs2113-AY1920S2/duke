package duke.taskList;

import duke.taskList.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> storage) {
        this.tasks = storage;
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static Task deleteTask(int deleteCount) {
        return tasks.remove(deleteCount);
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public Task getATask(int taskCount) {
        return this.tasks.get(taskCount);
    }

    public int size() {
        return tasks.size();
    }
}
