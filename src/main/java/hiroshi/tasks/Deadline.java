package hiroshi.tasks;

public class Deadline extends Task implements DatedEvents {

    public String date;

    /**
     * Constructor that initializes the an instance of the Event class.
     *
     * @param description Descripton of what the task is and any date associated with it.
     * @param by Date the deadline is in.
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = by;
    }

    /** Retuns the date associated with the task.  */
    public String returnDate(){
        return this.date;
    }

    /** Represents the icon that represents the task, i.e. [D] for deadline.  */
    @Override
    public String getTypeIcon() {
        return ("[D]");
    }

}
