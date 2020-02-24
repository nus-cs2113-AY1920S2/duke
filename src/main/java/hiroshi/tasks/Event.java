package hiroshi.tasks;

/** Represents a suclass of task that represents an event that will happen "at" a certain date.  */
public class Event extends Task implements DatedEvents {

    public String date;

    /**
     * Constructor that initializes the an instance of the Event class.
     *
     * @param description Descripton of what the task is and any date associated with it.
     * @param at Date the event is in.
     */
    public Event(String description, String at) {
        super(description);
        this.date = at;
    }

    /** Retuns the date associated with the task.  */
    public String returnDate(){
        return this.date;
    }

    /** Represents the icon that represents the task, i.e. [E] for event.  */
    @Override
    public String getTypeIcon() {
        return ("[E]");
    }

}
