package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline; // String containing deadline

    @Override
    public String getType() {
        return "D";
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(boolean isDone, String description, LocalDate deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toData(int taskId) {
        String dataLine = (taskId + "," + this.getType() + "," + this.isDone() + "," + this.getDescription() + ","
                + this.getDeadline());
        return dataLine;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: "
                + this.getDeadline().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}