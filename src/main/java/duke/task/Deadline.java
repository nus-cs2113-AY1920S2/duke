package duke.task;

/**
 * Type of Task that should be done before a specified date.
 */
public class Deadline extends Task {
    /** Icon used to represent a Deadline */
    public static final char DEADLINE_ICON = 'D';

    /** Date before which the Deadline should be done */
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String encodeTask() {
        return String.format("%s|%s|%s|%s", DEADLINE_ICON, isDone, description, by);
    }

    /**
     * Returns the object representation of an encoded Deadline.
     * @param encodedTask a string returned by method Task.encodeTask()
     * @return a Deadline object whose information was stored in encodedTask
     */
    public static Deadline decodeTask(String encodedTask) {
        String[] tokens = encodedTask.split("\\" + DELIMITER);
        boolean isDone = Boolean.parseBoolean(tokens[1]);
        String description = tokens[2];
        String by = tokens[3];
        Deadline deadline = new Deadline(description, by);
        if (isDone) {
            try {
                deadline.markAsDone();
            } catch (DukeException de) {
                // user feedback not required
            }
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[" + DEADLINE_ICON + "]" + super.toString() + " (by: " + by + ")";
    }
}
