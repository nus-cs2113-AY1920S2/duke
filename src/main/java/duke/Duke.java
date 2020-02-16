package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the main processor of the program.
 * Duke stores an internal TaskList that is manipulated
 * based on type of command given.
 */
public class Duke {
    
    /** The internal list of tasks stored in a TaskList. */
    private final TaskList taskList;
    
    /**
     * Constructor of Duke, if given a TaskList.
     * @param newTaskList
     */
    public Duke(TaskList newTaskList) {         
        this.taskList = new TaskList(newTaskList);       
    }
    
    /** 
     * Constructor of Duke that initializes a new TaskList.
     */
    public Duke() {
        this.taskList = new TaskList();
    }
    
    /** 
     * Represents the execution of deleting a task based on a given task ID.
     * 
     * @param taskId
     */
    public void executeDeleteCommand(int taskId) {
        this.taskList.deleteTask(taskId);
    }
    
    /** Represents the execution of listing all tasks in the TaskList. */
    public void executeListCommand() {
        this.taskList.listTask();
    }
    
    /** 
     * Represents the execution of completing a task based on a given task ID.
     * 
     * @param taskId
     */
    public void executeDoneCommand(int taskId) {
        this.taskList.completeTask(taskId);
    }
    
    /**
     * Represents the execution of adding a task.
     * 
     * @param task
     */
    public void executeAddCommand(Task task) {   
        this.taskList.addTask(task);
    }
    
    public TaskList getTaskList() {
        return this.taskList;
    }
}
