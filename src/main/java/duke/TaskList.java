package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task get(int i)
    {
        return tasks.get(i);
    }

    public int getSize()
    {
        return tasks.size();
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
