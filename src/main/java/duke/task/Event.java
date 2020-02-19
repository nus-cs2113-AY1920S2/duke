package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventTime;

    public LocalDate getEventTime() {
        return eventTime;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }

    public void setEventTime(LocalDate eventTime) {
        this.eventTime = eventTime;
    }

    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(boolean isDone, String description, LocalDate eventTime) {
        super(isDone, description);
        this.eventTime = eventTime;
    }

    @Override
    public String toData(int taskId) {
        String dataLine = (taskId + "," + this.getType() + "," + this.isDone() + "," + this.getDescription() + ","
                + this.getEventTime());
        return dataLine;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: "
                + this.getEventTime().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")");
    }
}
