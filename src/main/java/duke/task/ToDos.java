package duke.task;

/** 
 * Encapsulates the information of a To-Do task. A ToDos task 
 * contains a description, a TaskID and a boolean status to denote 
 * if it is completed or not.
 * 
 */
public class ToDos extends Task {
    
    /** 
     * Constructor of a ToDos task.
     * 
     * @param taskId The ID of the task to be constructed.
     * @param taskDecription The description of the task to be constructed.
     * @param isDone The completion status of the task to be constructed.
     */
    public ToDos(int taskId, String taskDescription, boolean isDone) {
        super(taskId, taskDescription, isDone);
    }
    
    /**  Constructs a ToDos task. This is used for makeTask() in the AddCommand. */
    public ToDos(int taskId, String taskDescription) {
        super(taskId, taskDescription);
    }
    
    /**
     * Set the ID of the task.
     * 
     * @param newTaskId The new task ID to be set.
     * @return A new immutable To-Do task.
     */
    @Override
    public Task setNewTaskId(int newTaskId) {
        return new ToDos(newTaskId, this.taskDescription, this.isDone);
    }
    
    /** A string representation of a ToDos task completion status using tick and cross symbols. */
    @Override
    public String taskWithSymbol() {
        return "[T]" 
                + ((this.isDone) ? Task.tickSymbol : Task.crossSymbol)
                + " " 
                + this.taskDescription;
    }

    /** 
     * Completes the task by changing completion status to be true.
     * 
     * @return A new immutable completed task.
     */
    @Override
    public ToDos makeDone() {
        return new ToDos(this.taskId, this.taskDescription, true);
    }

    /** A string representation of a ToDos task. */
    @Override
    public String toString() {
        return this.taskId 
                + "." 
                + this.taskWithSymbol();
    }
}
