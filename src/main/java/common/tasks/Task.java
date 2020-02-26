package common.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class Task implements Comparable<Task> {
    protected final String description;
    protected final Optional<LocalDate> date;
    protected final Optional<LocalTime> time;
    protected boolean isDone;
    
    /**
     * Constructor for Task class.
     * 
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.date = Optional.empty();
        this.time = Optional.empty();
        this.isDone = false;
    }
    
    /**
     * Constructor for Task class.
     * 
     * @param description Description of the task.
     * @param date Date of the task (only for Event and Deadline).
     * @param time Time of the task (optional for Event and Deadline).
     */
    public Task(String description, Optional<LocalDate> date, Optional<LocalTime> time) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.isDone = false;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Returns status icon depicting if task is done or not.
     * 
     * @return Indication if task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }
    
    public Optional<LocalDate> getOptionalDate() {
        return this.date;
    }
    
    public Optional<LocalTime> getOptionalTime() {
        return this.time;
    }
    
    public LocalDate getDate() {
        return this.date.get();
    }
    
    public LocalTime getTime() {
        return this.time.get();
    }
    
    /**
     * Sets task as done.
     * 
     * @return void.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    
    public boolean isDone() {
        return this.isDone;
    }
    
    /**
     * Custom comparable between two tasks. Sorted by date then time.
     * If time is not present, then time taken to be until 2359.
     * 
     * @param o Other task to compare against.
     * @return Integer depicting result of comparison.
     */
    @Override
    public int compareTo(Task o) {
        if (!this.date.get().equals(o.getDate())) {
            return this.date.get().compareTo(o.getDate());
        } else {
            if (!this.time.isPresent()) {
                if (o.getOptionalTime().isPresent()) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                if (!o.getOptionalTime().isPresent()) {
                    return -1;
                } else {
                    return this.time.get().compareTo(o.getTime());
                }
            }
        }
    }
    
    /**
     * Returns String representation of Task.
     * 
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}