package hiroshi.tasks;

/** Represents task represents something that has to be done without a date associated with it.  */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /** Returns [T], a string representing this subclass of Task. */
    @Override
    public String getTypeIcon() {
        return ("[T]");
    }
}