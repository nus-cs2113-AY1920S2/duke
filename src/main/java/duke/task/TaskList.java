package duke.task;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Collections;

import duke.task.*;

public class TaskList {
    public static int taskIdCounter = 1;
    private final List<Task> tasks;
    
    private TaskList(List<Task> oldTasks) {
        List<Task> newTasks = oldTasks.stream()
                .collect(Collectors.toList());
        
        this.tasks = newTasks;
    }
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public List<Task> getTasks() {
        return this.tasks;
    }
    
    public TaskList completeTask(int taskId) {
        List<Task> newTasks = this.tasks.stream()
                .map(task -> {
                    return ((task.getTaskId() == (taskId)) ? task.makeDone() : task);
                })
                .collect(Collectors.toList());
        
        return new TaskList(newTasks);
    }
    
    public TaskList addTask(Task task) {
        this.tasks.add(task);
        TaskList.taskIdCounter++;
        return new TaskList(this.tasks);
    }
    
    
}
