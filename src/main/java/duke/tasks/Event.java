package duke.tasks;

import duke.DukeException;

/**
 * Represents an event task with a task description and the
 * date of the event.
 */
public class Event extends Task {

    String timePeriod;

    public Event(String description, String t) throws DukeException {
        super(description.trim());
        if(description.isBlank() | t.isBlank())
        {
            throw new DukeException();
        }
        this.timePeriod = t.trim();
    }

    public String getTimePeriod() {
        return this.timePeriod;
    }

    @Override
    public String saveFormat() {
        return "e//" + super.saveFormat() + "//" + timePeriod;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timePeriod + ")";
    }
}
