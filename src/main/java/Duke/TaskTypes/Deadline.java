package Duke.TaskTypes;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[D]" + super.toString();
        }
        String[] bySplit = by.split(" ", 2);
        return "[D]" + super.toString() + "(" + bySplit[0] + ": " + bySplit[1] + ")";
    }
}
