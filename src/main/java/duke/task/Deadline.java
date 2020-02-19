package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task that must be completed before a set deadline.
 */
public class Deadline extends Task {

    /** Deadline of task. */
    private LocalDate deadline; // String containing deadline

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline of the task.
     */
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