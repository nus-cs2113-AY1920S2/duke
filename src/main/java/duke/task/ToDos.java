package duke.task;

/** 
 * Encapsulates the information of a todo.
 */
public class ToDos extends Task {

    /** Constructor of a ToDos.
     * 
     * @param taskId
     * @param taskName
     * @param isDone
     */
    public ToDos(int taskId, String taskName, boolean isDone) {
        super(taskId, taskName, isDone);
    }

    /** 
     * Constructor of a ToDos.
     * 
     * @param taskId
     * @param taskName
     */
    public ToDos(int taskId, String taskName) {
        super(taskId, taskName);
    }

    /**
     * Set the ID of the task.
     * 
     * @param newTaskId The new task ID to be set.
     * @return A new immutable task.
     */
    public Task setNewTaskId(int newTaskId) {
        return new ToDos(newTaskId, this.taskName, this.isDone);
    }
    
    /** A string representation of a deadline task with tick and cross symbols. */
    @Override
    public String taskWithSymbol() {
        return "[T]" 
                + ((this.isDone) ? Task.tickSymbol : Task.crossSymbol)
                + " " 
                + this.taskName;
    }

    /** 
     * Completes the task.
     * 
     * @return A new immutable completed task.
     */
    @Override
    public ToDos makeDone() {
        return new ToDos(this.taskId, this.taskName, true);
    }

    /** A string representation of a todo task. */
    @Override
    public String toString() {
        return this.taskId 
                + "." 
                + this.taskWithSymbol(); 			
    }
}
