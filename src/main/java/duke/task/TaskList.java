package duke.task;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static Task getIndex(int index) {
        return tasks.get(index);
    }

    public static int getSize() {
        return tasks.size();
    }

    public static void clearList() {
        tasks.clear();
    }

    public static void markTask(int index) {
        Task completedTask = tasks.get(index);
        completedTask.markAsDone();
    }

    public static void add(Task newItem) {
        tasks.add(newItem);
    }

    public static void removeTask(int index) {
        Task unwantedTask = tasks.get(index);
        tasks.remove(unwantedTask);
    }
}
