package duke;

import duke.taskmanager.TaskManager;

import java.util.ArrayList;
import java.util.List;

public class Tasklist {

    private static List<TaskManager> tasks;

    public Tasklist(Storage store) {
        if (store.getFileExits()) {
            tasks = Load.loadData();
        } else {
            tasks = new ArrayList<>();
        }
    }

    public static List<TaskManager> find(String input) {
        List<TaskManager> temp = new ArrayList<>();
        for (TaskManager task : tasks) {
            if (task.getTask().contains(input)) {
                temp.add(task);
            }
        }
        return temp;
    }

    public static List<TaskManager> getTasks() {
        return tasks;
    }
    public List<TaskManager> getTask() {
        return tasks;
    }

    public static void add(TaskManager task) {
        tasks.add(task);
    }

    public static void delete(int index) {
        tasks.remove(index);
    }

    public static int getSize() {
        return tasks.size();
    }

    public static void printTasks() {
        System.out.println("    Your current task list:");
        if (Task_No == 0){
            System.out.println("    You have no ongoing task.");
        } else {
            for (int i = 0; i < Task_No; i++){
                System.out.println("    Task " + i + ": " + manageTask[i]);
            }
        }
    }
}
