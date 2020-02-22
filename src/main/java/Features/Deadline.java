package Features;

/**
 * Extension of <code>Task</code> class specifying an <code>Deadline</code> task.
 */
public class Deadline extends Task{
    public Deadline(String description, String by) {
       super(description);
       this.timeToComplete = by;
    }
    public String getType() {
        return "Deadline";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.timeToComplete + ")";
    }
}
