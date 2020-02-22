package Features;

import java.time.LocalDate;

/**
 * Base class for the 3 specific task type: <code>Event Deadline Todo</code>.
 * Contain methods to check the elements of each task object.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String dateToCompleteString;
    protected LocalDate dateToCompleteLocalDate;
    protected String timeToComplete;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        dateToCompleteLocalDate = null;
        dateToCompleteString = "";
        timeToComplete = "";
    }
    public String getTimeToComplete() {
       if (dateToCompleteLocalDate != null) {
           return Integer.toString(dateToCompleteLocalDate.getMonthValue()) + " " + dateToCompleteLocalDate.getMonth() + " " +
                   dateToCompleteLocalDate.getYear() + ", " + dateToCompleteLocalDate.getDayOfWeek() + " " + timeToComplete;
       } else {
           return dateToCompleteString + " " + timeToComplete;
       }
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public String getDescription() {
        return this.description;
    }
    public String getType() {return "Task";}
    public void setIsDone(boolean isDone) {
       this.isDone = isDone;
    }
    public boolean getIsDone() {return isDone;}
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
