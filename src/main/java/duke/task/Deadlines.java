package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Deadlines extends Task {
    private final LocalDateTime dateTime;

    public Deadlines(int taskId, String taskName, 
            LocalDateTime dateTime, boolean isDone) {

        super(taskId, taskName, isDone);
        this.dateTime = dateTime;
    }
    
    public Deadlines(int taskId, String taskName, 
            String dateTime, boolean isDone) {

        super(taskId, taskName, isDone);
        this.dateTime = LocalDateTime.parse(dateTime);
    }

    public Deadlines(int taskId, String taskName, String dateTime) {
        super(taskId, taskName);
        this.dateTime = LocalDateTime.parse(dateTime);
    }
    
    @Override
    public Optional<String> getDate() {
        return Optional
                .ofNullable(this.dateTime
                .toLocalDate()
                .toString());
    }
    
    @Override
    public Task setNewTaskId(int newTaskId) {
        return new Deadlines(newTaskId, this.taskName, 
                this.dateTime, this.isDone);
    }

    @Override
    public String taskWithSymbol() {
        return "[D]" 
                + ((this.isDone) ? Task.tickSymbol : Task.crossSymbol)
                + " " 
                + this.taskName;
    }

    @Override
    public Deadlines makeDone() {
        return new Deadlines(this.taskId, this.taskName, 
                this.dateTime, true);
    }

    @Override
    public String toString() {
        return this.taskId 
                + "." 
                + this.taskWithSymbol()
                + " (by: " 
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + ")";
    }
}
