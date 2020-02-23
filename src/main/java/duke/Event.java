package duke;

import java.util.List;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected String icon = "[E]";
    protected String eventAt;

    /**
     * Constructor used when loading from the save file
     *
     * @param isDone integer representing a boolean, where 1 is done and 0 is not done, from save file.
     * @param description string representing task description, from the save file
     * @param evenAt string representing event location, from the save file
     */
    public Event(int isDone, String description, String evenAt) {
        super(description);
        this.eventAt = evenAt;

        if (isDone == 1) {
            super.markAsDone();
        }
    }

    public Event(String description, String eventAt){
        super(description);
        this.eventAt = eventAt;
    }

    /**
     * Overrides the default toString so that duke.Task gets printed in a specific format
     *
     * @return the formatted String to print
     */
    public String toString() {
        String toPrint = super.toString();
        toPrint = String.format("%s%s (at: %s)", this.icon, toPrint, this.eventAt);
        return toPrint;
    }

    public String getEventAt() {
        return this.eventAt;
    }

    @Override
    public void addIfMatchesKeyword(Task t, List<Task> foundTasks, String keyword) {
        String description = t.getDescription();
        String eventAt = getEventAt();
        if (description.contains(keyword) || eventAt.contains(keyword)) {
            foundTasks.add(t);
        }

    }
}
