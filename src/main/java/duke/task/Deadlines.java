package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Encapsulates the information of a deadline. A Deadline task
 * contains a description, a TaskID, a deadline requirement represented 
 * using taskDeadline and a boolean status to denote if it is completed or not.
 * 
 */
public class Deadlines extends Task {
    
    /** A taskDeadline to represent a deadline requirement for a task. */
    private final LocalDateTime taskDeadline;

    /** 
     * Constructor of a Deadlines task. 
     * 
     * @param taskId The ID of the task to be constructed.
     * @param taskDecription The description of the task to be constructed.
     * @param taskDeadline The deadline of the task to be constructed.
     * @param isDone The completion status of the task to be constructed.
     */
    public Deadlines(int taskId, String taskDescription, 
            LocalDateTime taskDeadline, boolean isDone) {

        super(taskId, taskDescription, isDone);
        this.taskDeadline = taskDeadline;
    }
    
    /**  Constructs a Deadlines task. This is used for makeTask() in the AddCommand. */
    public Deadlines(int taskId, String taskDescription, LocalDateTime taskDeadline) {
        super(taskId, taskDescription);
        this.taskDeadline = taskDeadline;
    }
    
    @Override
    public Optional<String> getDate() {
        return Optional
                .ofNullable(this.taskDeadline
                .toLocalDate()
                .toString());
    }
    
    public Optional<String> getTime() {
        return Optional
                .ofNullable(this.taskDeadline
                .toLocalTime()
                .toString());
    }
    
    /**
     * Set the ID of the task.
     * 
     * @param newTaskId The new task ID to be set.
     * @return A new immutable Deadline task.
     */
    @Override
    public Task setNewTaskId(int newTaskId) {
        return new Deadlines(newTaskId, this.taskDescription, 
                this.taskDeadline, this.isDone);
    }

    /** A string representation of a deadline task completion status using tick and cross symbols. */
    @Override
    public String taskWithSymbol() {
        return "[D]" 
                + ((this.isDone) ? Task.tickSymbol : Task.crossSymbol)
                + " " 
                + this.taskDescription;
    }

    /** 
     * Completes the task by changing completion status to be true.
     * 
     * @return A new immutable completed deadline task.
     */
    @Override
    public Deadlines makeDone() {
        return new Deadlines(this.taskId, this.taskDescription, 
                this.taskDeadline, true);
    }

    /** A string representation of a deadline task. */
    @Override
    public String toString() {
        return this.taskId 
                + "." 
                + this.taskWithSymbol()
                + " (by: " 
                + this.taskDeadline.format(
                        DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + ")";
    }
}
