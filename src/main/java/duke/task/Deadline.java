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

    /**
     * Sets the task's deadline.
     *
     * @param deadline The deadline to set.
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    /**
     * Constructs a deadline task.
     *
     * @param description Task description.
     * @param deadline Task deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a deadline task.
     *
     * @param isDone Set whether the task is already done or not.
     * @param description Task description.
     * @param deadline Task deadline.
     */
    public Deadline(boolean isDone, String description, LocalDate deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    /**
     * Converts the Task object to data representation to be stored in a data file.
     * File format:
     * taskId, taskType, taskIsDone, taskDesc, taskDate
     *
     * @param taskId ID of task.
     * @return String representing the Task object in comma-separated data format.
     */
    @Override
    public String toData(int taskId) {
        String dataLine = (taskId + "," + this.getType() + "," + this.isDone() + "," + this.getDescription() + ","
                + this.getDeadline());
        return dataLine;
    }

    /**
     * Represents the input task as a string.
     *
     * @return String representing the Task object.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: "
                + this.getDeadline().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}