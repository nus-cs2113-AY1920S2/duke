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

    public Optional<Task> deleteTask(int idx) {
        try {
            Task task = tasks.get(idx);
            tasks.remove(idx);
            return Optional.of(task);
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    public int getTotalTaskNum() {
        return tasks.size();
    }

    public Task getTaskAtIdx(int idx) {
        return tasks.get(idx);
    }

    public void markTaskAsDone(String indexStr) {
        try {
            int taskIdx = Integer.parseInt(indexStr);
            Task task = tasks.get(taskIdx - 1);
            task.markAsDone();
            System.out.println("Congratulations! You've successfully marked the following task as done:");
            System.out.println(task.toString());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid task number");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The number you entered does not match any task in your list");
        }
    }
}
