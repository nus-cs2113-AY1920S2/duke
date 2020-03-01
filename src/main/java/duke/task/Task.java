package duke.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "✓";
        } else {
            return "✗"; //return tick or X symbols
        }
    }

    public String getType() {
        return "";
    }

    public String getDescription() {
        return description;
    }

    public int getIsDone() {
        if (isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    public LocalDate getTime() {
        return LocalDate.parse("1998-01-16");
    }

    public String getTimeFormatted() {
        return "";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description);
    }
}
