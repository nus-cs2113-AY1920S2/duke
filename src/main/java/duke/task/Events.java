package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Encapsulates the information of an Event. An Events task
 * contains a description, a TaskID, a duration represented using
 * taskStartDateTime and taskEndDateTime and a boolean status to denote 
 * if it is completed or not.
 * 
 */
public class Events extends Task {
    
    private final LocalDateTime taskStartDateTime;
    
    private final LocalDateTime taskEndDateTime;
    
    /**
     * Constructor of an Events task.
     * 
     * @param taskId The ID of the task to be constructed.
     * @param taskDecription The description of the task to be constructed.
     * @param taskStartDateTime The starting date and time of the event.
     * @param taskEndDateTime The ending date and time of the event.
     * @param isDone The completion status of the task to be constructed.
     */
    public Events(int taskId, String taskDescription, 
            LocalDateTime taskStartDateTime, LocalDateTime taskEndDateTime,
            boolean isDone) {

        super(taskId, taskDescription, isDone);
        this.taskStartDateTime = taskStartDateTime;
        this.taskEndDateTime = taskEndDateTime;
    }
    
    /**  Constructs an Events task. This is used for makeTask() in the AddCommand. */
    public Events(int taskId, String taskDescription, LocalDateTime taskStartDateTime,
            LocalDateTime taskEndDateTime) {
        super(taskId, taskDescription);
        
        this.taskStartDateTime = taskStartDateTime;
        this.taskEndDateTime = taskEndDateTime;
    }

    
    public Optional<String> getStartDate() {
        return Optional
                .ofNullable(this.taskStartDateTime
                .toLocalDate()
                .toString());
    }
    
    public Optional<String> getStartTime() {
        return Optional
                .ofNullable(this.taskStartDateTime
                .toLocalTime()
                .toString());
    }
   
    public Optional<String> getEndDate() {
        return Optional
                .ofNullable(this.taskEndDateTime
                .toLocalDate()
                .toString());
    }
    
    public Optional<String> getEndTime() {
        return Optional
                .ofNullable(this.taskEndDateTime
                .toLocalTime()
                .toString());
    }
    
    /**
     * Set the ID of the task.
     * 
     * @param newTaskId The new task ID to be set.
     * @return A new immutable task.
     */
    @Override
    public Task setNewTaskId(int newTaskId) {
        return new Events(newTaskId, this.taskDescription, 
                this.taskStartDateTime, this.taskEndDateTime, this.isDone);
    }

    /** A string representation of a deadline task with tick and cross symbols. */
    @Override
    public String taskWithSymbol() {
        return "[E]" 
                + ((this.isDone) ? Task.tickSymbol : Task.crossSymbol)
                + " " 
                + this.taskDescription;
    }

    /** 
     * Completes the task.
     * 
     * @return A new immutable completed task.
     */
    @Override
    public Events makeDone() {
        return new Events(this.taskId, this.taskDescription, 
                this.taskStartDateTime, this.taskEndDateTime, true);
    }

    /** A string representation of an event task. */
    @Override
    public String toString() {
        return this.taskId 
                + "."  
                + this.taskWithSymbol()
                + " (on: " 
                + this.taskStartDateTime.format(
                        DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to "
                + this.taskEndDateTime.format(
                        DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + ")";
    }
}
