package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the main processor of the program. Duke executes a given
 * command and perform operations such as adding or deleting tasks 
 * on its internal TaskList.
 * 
 * Note that Duke has only one final TaskList. Any command executed
 * that causes a state change in this TaskList will have to clear the
 * current TaskList and perform a deep copy of the manipulated 
 * TaskList derived into its current TaskList.
 * 
 */
public class Duke {
    
    /** The internal TaskList stored in Duke's object. */
    private final TaskList taskList;
    
    /**
     * Constructor of Duke, if given a new TaskList.
     * 
     * @param newTaskList A new TaskList that is to be deep copied 
     *                    into Duke's current taskList.
     */
    public Duke(TaskList newTaskList) {         
        this.taskList = new TaskList(newTaskList);       
    }
    
    /** Constructor of Duke when the program initializes. */
    public Duke() {
        this.taskList = new TaskList();
    }
    
    public void executeFindCommand(String keyword) {
        this.taskList.findTask(keyword);
    }
    
    public void executeFilterCommand(String date) {
        this.taskList.filterTask(date);
    }
    
    public void executeDeleteCommand(int taskId) {
        this.taskList.deleteTask(taskId);
    }
    
    public void executeListCommand() {
        this.taskList.listTask();
    }
    
    public void executeDoneCommand(int taskId) {
        this.taskList.completeTask(taskId);
    }
    
    public void executeAddCommand(Task task) {   
        this.taskList.addTask(task);
    }
    
    public TaskList getTaskList() {
        return this.taskList;
    }
}
