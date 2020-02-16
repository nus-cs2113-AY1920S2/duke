package duke.task;


import static misc.Messages.MESSAGE_INCORRECT_DATE_FORMAT_INPUT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Encapsulates the information of an event.
 */
public class Events extends Task {
    
    private final LocalDateTime dateTime;
    
    public Events(int taskId, String taskName, 
            LocalDateTime dateTime, boolean isDone) {

        super(taskId, taskName, isDone);
        this.dateTime = dateTime;
    }

    public Events(int taskId, String taskName, 
            String dateTime, boolean isDone) {

        super(taskId, taskName, isDone);
        
        try {
            this.dateTime = LocalDateTime.parse(dateTime);
        } catch (DateTimeParseException dtpe) {
            throw new InvalidTaskArgumentException(MESSAGE_INCORRECT_DATE_FORMAT_INPUT);
        }
    }
    
    /**
     * Constructor of an events task.
     * 
     * @param taskId
     * @param taskName
     * @param dateTime
     */
    public Events(int taskId, String taskName, String dateTime) {
        super(taskId, taskName);

        try {
            this.dateTime = LocalDateTime.parse(dateTime);
        } catch (DateTimeParseException dtpe) {
            throw new InvalidTaskArgumentException(MESSAGE_INCORRECT_DATE_FORMAT_INPUT);
        }
    }
;
    @Override
    public Optional<String> getDate() {
        return Optional
                .ofNullable(this.dateTime
                .toLocalDate()
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
        return new Events(newTaskId, this.taskName, 
                this.dateTime, this.isDone);
    }

    /** A string representation of a deadline task with tick and cross symbols. */
    @Override
    public String taskWithSymbol() {
        return "[E]" 
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
    public Events makeDone() {
        return new Events(this.taskId, this.taskName, 
                this.dateTime, true);
    }

    /** A string representation of an event task. */
    @Override
    public String toString() {
        return this.taskId 
                + "."  
                + this.taskWithSymbol()
                + " (at: " 
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + ")";
    }
}
