package duke.tasks;

import java.time.LocalDate;

public class Event extends Task implements DatedEvents {

    protected LocalDate date;

    public Event(String description, LocalDate at) {
        super(description);
        this.date = at;
    }

    public LocalDate returnDate(){
        return this.date;
    }

    @Override
    public String getTypeIcon() {

        return ("[E]");
    }
}
