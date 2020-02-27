package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public abstract class Task {

    public static final String TICK = "✓";
    public static final String CROSS = "✘";

    protected String description;
    //protected String date;
    protected LocalDate date;
    protected boolean isDone;
    protected Integer time;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public Task(String description) {
        this.description = description;
        this.date = null;
        this.isDone = false;
        this.time = null;
    }

    public Task(String description, String date, String time) {

        this.description = description;
        this.date = LocalDate.parse(date, formatter);
        this.isDone = false;
        this.time = Integer.parseInt(time);
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
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

    public Integer getTime() {
        return time;
    }

    public void printAddDetails(int taskCounter) {
        System.out.println("The following task has been added:\n[" + getTaskType() +"][" + getStatusIcon() + "] " + getDescription());
        System.out.println("\nYou've got " + taskCounter + " task(s) in the list!\n");
    }

    public abstract void printListDetails(int count);

}
