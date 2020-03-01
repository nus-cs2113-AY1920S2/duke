package duke;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDeadline() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDeadline() + ")" ;
    }

}
