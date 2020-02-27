package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    public boolean getIsOn(LocalDate date) {
        return false;
    }

    public boolean getIsBy(LocalDateTime dateTime) {
        return false;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "T," + done + "," + description;
    }
}
