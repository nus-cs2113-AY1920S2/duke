package duke.task;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.task.Task;
import static misc.Messages.MESSAGE_COMMAND_LIST_TASK;
import static misc.Messages.MESSAGE_DONE_COMMNAND_INDEX_OUT_OF_BOUNDS;

public class TaskList {
    public final int FIRST_ELEMENT_INDEX = 0;
    public static int taskIdCounter = 1;
    private final List<Task> tasks;
    
    public TaskList(TaskList oldTaskList) {
        this.tasks = oldTaskList.getTasks()
                .stream()
                .collect(Collectors.toList());
    }
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public List<Task> getTasks() {
        return this.tasks;
    }
    
    public void listTask() {
        System.out.println(MESSAGE_COMMAND_LIST_TASK);
        
        this.tasks.stream()
                .forEachOrdered(System.out::println);
            
        System.out.println("\n");
    }
    
    public void completeTask(int taskId) throws IndexOutOfBoundsException {  
        if (taskId > this.tasks.size()) {
            throw new IndexOutOfBoundsException(
                    MESSAGE_DONE_COMMNAND_INDEX_OUT_OF_BOUNDS);
        }
        
        List<Task> newTasks = this.tasks.stream()
                .map(task -> {
                    return ((task.getTaskId() == taskId) ? task.makeDone() : task);
                })
                .collect(Collectors.toList());
        
        createCompleteTaskMessage(taskId);
        this.tasks.clear();
        this.tasks.addAll(newTasks);
    }
    
    public void addTask(Task task) {
        createAddTaskMessage(task);     
        this.tasks.add(task);
        TaskList.taskIdCounter++;       
    }
    
    public Task getTask(int taskId) {
        Task task = this.tasks
                .stream()
                .filter(x -> x.getTaskId() == taskId)
                .map(x -> x.makeDone())
                .collect(Collectors.toList())
                .get(FIRST_ELEMENT_INDEX);
        return task;
    }
    
    public void createDeleteTaskMessage(int taskId) {
        String output = ("Nice! I've removed this task:\n"
                + "  " 
                + getTask(taskId).taskWithSymbol()
                + "\nNow you have " 
                + TaskList.taskIdCounter
                + " tasks in the list.\n");
        System.out.println(output);
    }
    
    public void createCompleteTaskMessage(int taskId) {
        String output = ("Nice! I've marked this task as done:\n"
                + "  " 
                + getTask(taskId).taskWithSymbol()
                + ("\n"));
        System.out.println(output);
    }
    
    public void createAddTaskMessage(Task task) {
        String message = "";
        message += ("Got it. I've added this task:\n"
                + "  " 
                + task
                + "\nNow you have " 
                + TaskList.taskIdCounter
                + " tasks in the list.\n");
        System.out.println(message);
    }
}
