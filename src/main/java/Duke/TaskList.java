package Duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    int size;

    public TaskList() {
        this.tasks = new ArrayList<>();
        size = 0;
    }

    public void addTask (Task task) {
        tasks.add(task);
        size++;
    }


    public void deleteTask (Task task) {
        tasks.remove(task);
        size--;
    }

    public Task get (int i) {
        return tasks.get(i);
    }


}
