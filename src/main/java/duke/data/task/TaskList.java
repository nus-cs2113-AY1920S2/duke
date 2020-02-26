package duke.data.task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public boolean find(String description, TaskList tasklist) {
        for(Task task : tasklist) {
            if(task.getDescription().equals(description)) {
                return true;
            }
        }
        return false;
    }
}