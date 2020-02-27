package duke.tasklist;

import duke.tasklist.task.Task;

import java.util.ArrayList;

/**
 * Contains the task list.
 * It has operations to add/delete tasks in the list.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static Task deleteTask(int deleteCount) {
        return tasks.remove(deleteCount);
    }

    public Task getATask(int taskCount) {
        return this.tasks.get(taskCount);
    }

    public int size() {
        return tasks.size();
    }
}
