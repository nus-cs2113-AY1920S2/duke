package hiroshi.tasks;

import java.time.LocalDate;

public class Deadline extends Task implements DatedEvents {

    protected LocalDate date;

    /**
     * Initializes an instance of the Event class.
     *
     * @param description Descripton of what the task is and any date associated with it.
     * @param by Date the deadline is in.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.date = by;
    }

    /** Returns the date associated with the task.  */
    public LocalDate returnDate(){
        return this.date;
    }

    /** Represents the icon that represents the task, i.e. [D] for deadline.  */
    @Override
    public String getTypeIcon() {
        return ("[D]");
    }
}