package duke.tasks;

import java.time.LocalDate;

public class Deadline extends Task implements DatedEvents {
    protected LocalDate date;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.date = by;
    }

    public LocalDate returnDate(){
        return this.date;
    }

    @Override
    public String getTypeIcon() {
        return ("[D]");
    }


}
