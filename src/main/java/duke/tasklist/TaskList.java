package duke.tasklist;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }
    
    public int getListSize() {
        return tasks.size();
    }
    
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }
}
