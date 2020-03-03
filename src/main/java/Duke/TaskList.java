package Duke;

import Duke.Tasks.Task;

import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public static void addTask(Task task) {
        taskList.add(task);
    }

    public static void deleteTask(Task task) {
        taskList.remove(task);
    }

    public static int getSize() {
        return taskList.size();
    }

    public static Task fetchTask(int i) {
        return taskList.get(i);
    }

    public static String checkSingular() {
        if (getSize() == 1) {
            return "";
        }
        return "s";
    }
}
