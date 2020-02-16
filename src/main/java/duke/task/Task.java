package duke.task;

/**
 * Encapsulates the information of a task.
 */
import java.util.Optional;

public class Task {
    
    /** The description of a task. */
    protected final String taskName;
    
    /** The unique ID of a task. */
    protected final int taskId;
    
    /** Represents whether a task is done or not. */
    protected boolean isDone;
    
    /** String representation of a tick symbol. */
    protected static final String tickSymbol = "[✓]";
    
    /** String representation of a cross symbol. */
    protected static final String crossSymbol = "[✗]";

    /** 
     * Constructor of a task.
     * 
     * @param taskId
     * @param taskName
     * @param isDone
     */
    protected Task(int taskId, String taskName, boolean isDone) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /** 
     * Constructor of a task.
     * 
     * @param taskId
     * @param taskName
     */
    protected Task(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.isDone = false;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }
    
    /**
     * Set the ID of the task.
     * 
     * @param newTaskId The new task ID to be set.
     * @return A new immutable task.
     */
    public Task setNewTaskId(int newTaskId) {
        return new Task(newTaskId, this.taskName, this.isDone);
    }

    /** A string representation of a deadline task with tick and cross symbols. */
    public String taskWithSymbol() {
        return ((this.isDone) ? tickSymbol : crossSymbol)
                + "   " 
                + this.taskName;
    }

    /** 
     * Completes the task.
     * 
     * @return A new immutable completed task.
     */
    public Optional<String> getDate() {
        return Optional.empty();
    }

    public Task makeDone() {
        return new Task(this.taskId, this.taskName, true);
    }

    /** 
     * An equals method to test if both tasks are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } 
        if (obj instanceof Task) {
            Task task = (Task) obj;
            return task.taskId == this.taskId;
        }
        return false;
    }

    /** A string representation of an event task. */
    @Override
    public String toString() {
        return this.taskId + "." + this.taskWithSymbol(); 			
    }
}
