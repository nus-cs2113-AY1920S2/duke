package duke.task;

import duke.DukeException;

public class Deadline extends Task {
    public static final char DEADLINE_ICON = 'D';

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String encodeTask() {
        return String.format("%s|%s|%s|%s", DEADLINE_ICON, isDone, description, by);
    }

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
