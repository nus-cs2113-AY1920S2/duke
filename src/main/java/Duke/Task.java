package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Default task class, provides skeleton for specific task types
 */
public abstract class Task {

    /*
    Symbols do not work on windows
    public static final char TICK = '\u2713';
    public static final char CROSS = '\u2718';
    */

    public static final char TICK = 'Y'; //Yes
    public static final char CROSS = 'N'; //No

    protected String description;
    protected LocalDate date;
    protected boolean isDone;
    protected String time;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * Constructs for task types without date field
     *
     * @param description information about the task
     */
    public Task(String description) {
        this.description = description;
        this.date = null;
        this.isDone = false;
        this.time = null;
    }

    /**
     * Constructor for task types with date and time field
     *
     * @param description information about the task
     * @param date Stored in a YYYY-MM-DD format
     */
    public Task(String description, String date, String time) {
        this.description = description;
        this.date = LocalDate.parse(date, formatter);
        this.isDone = false;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns symbol for status of task
     *
     * @return tick for done, cross for not done
     */
    public char getStatusIcon() {
        return (isDone ? TICK : CROSS); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskType() {
        return null;
    }

    public String getTime() {
        return time;
    }

    /**
     * Prints details of task upon adding
     * Default is set for tasks without date
     *
     * @param taskCounter current index of task
     */
    public void printAddDetails(int taskCounter) {
        System.out.println("The following task has been added:\n[" + getTaskType() +"][" + getStatusIcon() + "] " + getDescription());
        System.out.println("\nYou've got " + taskCounter + " task(s) in the list!\n");
    }

    public abstract void printListDetails(int count);

}
