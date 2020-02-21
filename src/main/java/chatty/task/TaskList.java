package chatty.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int idx) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        return task;
    }

    public int getTotalTaskNum() {
        return tasks.size();
    }

    public Task getTaskAtIdx(int idx) {
        return tasks.get(idx);
    }

    public Task markTaskAsDone(int idx) {
        Task task = tasks.get(idx);
        task.markAsDone();
        return task;
    }
}
