package duke.tasks;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }
    public List<Task> getTaskList() {
        return this.tasks;
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    public void removeTask(Task task) {
        this.tasks.remove(task);
    }
    public int getLength() {
        return this.tasks.size();
    }
    public void clearList() {
        this.tasks.clear();
    }

}
