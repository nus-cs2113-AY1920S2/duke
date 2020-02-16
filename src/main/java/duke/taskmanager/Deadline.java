package duke.taskmanager;

public class Deadline extends TaskManager {
    protected String by;
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[D]" + super.toString();
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
