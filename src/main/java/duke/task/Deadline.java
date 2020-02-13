package duke.task;

public class Deadline extends Task {
    private String by; // String containing deadline

    @Override
    public String getType() {
        return "D";
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toData(int taskId) {
        String dataLine = taskId + "," + this.getType() + "," + this.isDone() + "," + this.getDescription() + "," + this.getBy();
        return dataLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}