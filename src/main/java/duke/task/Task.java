package duke.task;

import java.util.Optional;

/**
 * Encapsulates the information of a task. A task contains
 * a description, a TaskID and a boolean status to denote 
 * if it is completed or not.
 * 
 */
public class Task {
    
    /** The description of a task. */
    protected final String taskDescription;
    
    /** The unique ID of a task. */
    protected final int taskId;
    
    /** Represents whether a task is done or not. */
    protected boolean isDone;
    
    /** A tick symbol to represent a completed task. */
    protected static final String tickSymbol = "[/]";
    
    /** A cross symbol to represent an incomplete task. */
    protected static final String crossSymbol = "[X]";

    /** 
     * Constructor of a immutable task.
     * 
     * @param taskId The ID of the task to be created.
     * @param taskDescription The description of the task to be created.
     * @param isDone The status of the task to be created.
     */
    protected Task(int taskId, String taskDescription, boolean isDone) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    /** 
     * Constructs an immutable incomplete task.
     * 
     * @param taskId The ID of the task to be created.
     * @param taskDescription The description of the task to be created.
     */
    protected Task(int taskId, String taskDescription) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public String getTaskName() {
        return this.taskDescription;
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
        return new Task(newTaskId, this.taskDescription, this.isDone);
    }

    /** A string representation of a task completion status using tick and cross symbols. */
    public String taskWithSymbol() {
        return ((this.isDone) ? tickSymbol : crossSymbol)
                + "   " 
                + this.taskDescription;
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
        return new Task(this.taskId, this.taskDescription, true);
    }

    /** Returns true if both tasks has the same taskId. */
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

    /** A string representation of a task object. */
    @Override
    public String toString() {
        return this.taskId + "." + this.taskWithSymbol();
    }
}
