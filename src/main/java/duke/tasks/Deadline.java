package duke.tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", this.type, super.toString(), by);
    }
    
    @Override
    public String getFileString() {
        return String.format("%s | %d | %s | %s", type, isDone ? 1 : 0, description, by);
    }
}
